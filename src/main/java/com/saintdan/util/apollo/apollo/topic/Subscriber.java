package com.saintdan.util.apollo.apollo.topic;

import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.enums.ApiType;
import com.saintdan.util.apollo.enums.ErrorType;
import com.saintdan.util.apollo.exception.MQTTException;
import com.saintdan.util.apollo.exception.SubscriberException;
import com.saintdan.util.apollo.util.CallbackImpl;
import com.saintdan.util.apollo.util.ConnectionListenerImpl;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * Packaging api for subscribe.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 5/28/15
 * @since JDK1.8
 */
public class Subscriber {
    private final static short KEEP_ALIVE = 30;
    private String host;
    private int port;
    private String username;
    private String password;
    private ApiType apiType;
    private QoS qoSType;

    /**
     * Constructor of Subscriber.
     *
     * @param config
     *                  ConfigBO {@link ConfigBO}
     */
    public Subscriber(ConfigBO config) {
        this.host = config.getHost();
        this.port = config.getPort();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.apiType = config.getApiType();
        this.qoSType = config.getQoSType();
    }

    /**
     * Subscribe messages from a topic.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     *
     * @throws SubscriberException
     * @throws MQTTException
     */
    public void sub(TopicBO topic) throws SubscriberException, MQTTException {

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
                    throw new SubscriberException(ErrorType.SUB0001);
            }
        } catch (URISyntaxException e) {
            throw new MQTTException(ErrorType.MQT0001);
        }
    }

    /**
     * Blocking API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     *
     * @throws SubscriberException
     */
    private void blocking(TopicBO topic, MQTT mqtt) throws SubscriberException {
    }

    /**
     * Future API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     *
     * @throws SubscriberException
     */
    private void future(TopicBO topic, MQTT mqtt) throws SubscriberException {
    }

    /**
     * Callback API.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param mqtt
     *                  MQTT {@link MQTT}
     *
     * @throws SubscriberException
     */
    private void callback(final TopicBO topic, MQTT mqtt) throws SubscriberException {
        final CallbackConnection connection = mqtt.callbackConnection();

        final CountDownLatch finished = new CountDownLatch(1);

        connection.connect(new CallbackImpl(){
            @Override
            public void onSuccess(Object value) {
                Topic destination = new Topic(topic.getDestination(), QoS.AT_MOST_ONCE);
                connection.subscribe(new Topic[] {destination}, new CallbackImpl(){
                    @Override
                    public void onSuccess(Object value) {
                        System.out.println("Subscription successful. Waiting for messages...");
                    }
                });
            }
        });

        connection.listener(new ConnectionListenerImpl() {
            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                String payload = body.length() > 0 ? body.utf8().toString(): "Unavailable: ERROR";
                System.out.println("Received message: " + payload);

                if (payload.trim().equalsIgnoreCase("END")) {
                    finished.countDown();
                }
                ack.run();
            }
        });

        try {
            finished.await();
        } catch (InterruptedException e) {
            throw new SubscriberException(ErrorType.SUB0030);
        }
    }

}
