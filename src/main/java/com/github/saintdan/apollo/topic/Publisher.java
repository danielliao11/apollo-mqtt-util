package com.github.saintdan.apollo.topic;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import com.github.saintdan.enums.ErrorType;
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
    private String username;
    private String password;
    private ApiType apiType;
    private QoS qoSType;

    private boolean isPublished = false;

    /**
     * Constructor of Publisher.
     *
     * @param config    ConfigBO {@link ConfigBO}
     */
    public Publisher(ConfigBO config) {
        this.host = config.getHost();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.apiType = config.getApiType();
        this.qoSType = config.getQoSType();
    }

    /**
     * Publish messages to a topic.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @return boolean  isSuccess
     */
    public boolean pub(TopicBO topic) {
        boolean isSuccess = false;
        try {
            MQTT mqtt = new MQTT();
            mqtt.setHost(host);
            mqtt.setUserName(username);
            mqtt.setPassword(password);
            mqtt.setKeepAlive(KEEP_ALIVE);

            switch (apiType) {
                case BLOCKING:
                    isSuccess = blocking(topic, mqtt);
                    if (!isSuccess) {
                        throw new PublishException(ErrorType.PUB0010);
                    }
                    break;
                case FUTURE:
                    isSuccess = future(topic, mqtt);
                    if (!isSuccess) {
                        throw new PublishException(ErrorType.PUB0020);
                    }
                    break;
                case CALLBACK:
                    isSuccess = callback(topic, mqtt);
                    if (!isSuccess) {
                        throw new PublishException(ErrorType.PUB0030);
                    }
                    break;
                default:
                    throw new PublishException(ErrorType.PUB0001);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (PublishException e) {
            System.out.println("Publish failed, and the exception is:" + e);
        }
        return isSuccess;
    }

    /**
     * Blocking API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     * @return boolean  isPublished
     */
    private boolean blocking(TopicBO topic, MQTT mqtt) {
        BlockingConnection connection = mqtt.blockingConnection();
        try {
            connection.connect();
        } catch (Exception e) {
            System.out.println("Blocking connection error: " + e + ErrorType.CNN0010);
        }

        while (!connection.isConnected());

        if (connection.isConnected()) {
            try {
                connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false);
                isPublished = true;
                connection.disconnect();
            } catch (Exception e) {
                System.out.println("Blocking publish error: " + e + ErrorType.PUB0010);
            }
        }
        return isPublished;
    }

    /**
     * Future API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     * @return boolean  isPublished
     */
    private boolean future(TopicBO topic, MQTT mqtt) {
        FutureConnection connection = mqtt.futureConnection();
        connection.connect();

        while (!connection.isConnected());

        if (connection.isConnected()) {
            connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false);
            isPublished = true;
            connection.disconnect();
        }
        return isPublished;
    }

    /**
     * Callback API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     * @return boolean  isPublished
     */
    private boolean callback(TopicBO topic, MQTT mqtt) {

        final CallbackConnection connection = mqtt.callbackConnection();
        final CountDownLatch finished = new CountDownLatch(1);

        connection.connect(new CallbackImpl(){
            @Override
            public void onSuccess(Object value) {
                connection.publish(topic.getDestination(), topic.getContent().getBytes(), qoSType, false, null);
                finished.countDown();
            }
        });

        try {
            finished.await();
            isPublished = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isPublished;
    }

}
