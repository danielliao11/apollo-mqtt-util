package com.github.saintdan.apollo.topic;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import com.github.saintdan.enums.ErrorType;
import com.github.saintdan.exception.ConnectionException;
import com.github.saintdan.exception.MQTTException;
import com.github.saintdan.exception.PublishException;
import com.github.saintdan.util.CallbackImpl;
import com.github.saintdan.util.ConnectionListenerImpl;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Packaging api for publish.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 5/28/15
 * @since JDK1.8
 */
public class Publisher {

    private final static short KEEP_ALIVE = 30;
    private String host;
    private int port;
    private String username;
    private String password;
    private ApiType apiType;
    private QoS qoSType;

    /**
     * Constructor of Publisher.
     *
     * @param config
     *                  ConfigBO {@link ConfigBO}
     */
    public Publisher(ConfigBO config) {
        this.host = config.getHost();
        this.port = config.getPort();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.apiType = config.getApiType();
        this.qoSType = config.getQoSType();
    }

    /**
     * Publish messages to a topic.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @return boolean
     *                  publish or not
     *
     * @throws ConnectionException
     * @throws PublishException
     * @throws MQTTException
     */
    public boolean pub(TopicBO topic) throws ConnectionException, PublishException, MQTTException {
        try {
            MQTT mqtt = new MQTT();
            mqtt.setHost("tcp://" + host + ":" + port);
            mqtt.setUserName(username);
            mqtt.setPassword(password);
            mqtt.setKeepAlive(KEEP_ALIVE);

            switch (apiType) {
                case BLOCKING:
                    blocking(topic, mqtt);
                    break;
                case FUTURE:
                    future(topic, mqtt);
                    break;
                case CALLBACK:
                    callback(topic, mqtt);
                    break;
                default:
                    throw new PublishException(ErrorType.PUB0001);
            }
        } catch (URISyntaxException e) {
            throw new MQTTException(ErrorType.MQT0001);
        }
        return true;
    }

    /**
     * Blocking API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     * @return
     *                  publish or not
     *
     * @throws ConnectionException
     * @throws PublishException
     */
    private boolean blocking(TopicBO topic, MQTT mqtt) throws ConnectionException, PublishException {
        BlockingConnection connection = mqtt.blockingConnection();
        try {
            connection.connect();
        } catch (Exception e) {
            throw new ConnectionException(ErrorType.CNN0010);
        }

        while (!connection.isConnected());

        if (connection.isConnected()) {
            try {
                connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false);
                connection.disconnect();
            } catch (Exception e) {
                throw new PublishException(ErrorType.PUB0010);
            }
        }
        return true;
    }

    /**
     * Future API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     * @return
     *                  publish or not
     *
     * @throws ConnectionException
     * @throws PublishException
     */
    private boolean future(TopicBO topic, MQTT mqtt) throws ConnectionException, PublishException {
        FutureConnection connection = mqtt.futureConnection();
        try {
            connection.connect();
        } catch (Exception e) {
            throw new ConnectionException(ErrorType.CNN0020);
        }

        while (!connection.isConnected());

        if (connection.isConnected()) {
            try {
                connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false);
                connection.disconnect();
            } catch (Exception e) {
                throw new PublishException(ErrorType.PUB0020);
            }
        }
        return true;
    }

    /**
     * Callback API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     * @return
     *                  publish or not
     *
     * @throws ConnectionException
     * @throws PublishException
     */
    private boolean callback(final TopicBO topic, MQTT mqtt) throws ConnectionException, PublishException {

        final CallbackConnection connection = mqtt.callbackConnection();
        final CountDownLatch finished = new CountDownLatch(1);

        try {
            connection.connect(new CallbackImpl(){
                @Override
                public void onSuccess(Object value) {
                    connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false, null);
                    finished.countDown();
                }
            });
        } catch (Exception e) {
            throw new ConnectionException(ErrorType.CNN0030);
        }

        try {
            finished.await();
        } catch (InterruptedException e) {
            throw new PublishException(ErrorType.PUB0030);
        }
        return true;
    }

}
