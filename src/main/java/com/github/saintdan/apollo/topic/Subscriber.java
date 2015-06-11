package com.github.saintdan.apollo.topic;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import com.github.saintdan.enums.ErrorType;
import com.github.saintdan.exception.SubscriberException;
import com.github.saintdan.util.CallbackImpl;
import com.github.saintdan.util.ConnectionListenerImpl;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * Packaging api for subscribe.
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 5/28/15
 * @since JDK1.8
 */
public class Subscriber {
    private final static short KEEP_ALIVE = 30;

    private String host;
    private String username;
    private String password;
    private ApiType apiType;
    private QoS qoSType;

    /**
     * Constructor of Subscriber.
     *
     * @param config    ConfigBO {@link ConfigBO}
     */
    public Subscriber(ConfigBO config) {
        this.host = config.getHost();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.apiType = config.getApiType();
        this.qoSType = config.getQoSType();
    }

    /**
     * Subscribe messages from a topic.
     *
     * @param topic     TopicBO {@link TopicBO}
     */
    public void sub(TopicBO topic) {

        try {
            MQTT mqtt = new MQTT();
            mqtt.setHost(host);
            mqtt.setUserName(username);
            mqtt.setPassword(password);
            mqtt.setKeepAlive(KEEP_ALIVE);

            switch (apiType) {
                case BLOCKING:
                    try {
                        blocking(topic, mqtt);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new SubscriberException(ErrorType.SUB0010);
                    }
                    break;
                case FUTURE:
                    try {
                        future(topic, mqtt);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new SubscriberException(ErrorType.SUB0020);
                    }
                    break;
                case CALLBACK:
                    try {
                        callback(topic, mqtt);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new SubscriberException(ErrorType.SUB0030);
                    }
                    break;
                default:
                    throw new SubscriberException(ErrorType.SUB0001);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SubscriberException e) {
            System.out.println("Subscribe failed, and the exception is:" + e);
        }
    }

    /**
     * Blocking API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     */
    private void blocking(TopicBO topic, MQTT mqtt) {
    }

    /**
     * Future API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     */
    private void future(TopicBO topic, MQTT mqtt) {
    }

    /**
     * Callback API.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param mqtt      MQTT {@link MQTT}
     */
    private void callback(TopicBO topic, MQTT mqtt) {
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
            e.printStackTrace();
        }
    }

}
