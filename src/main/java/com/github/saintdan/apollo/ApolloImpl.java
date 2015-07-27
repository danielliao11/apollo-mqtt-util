package com.github.saintdan.apollo;

import com.github.saintdan.apollo.topic.Publisher;
import com.github.saintdan.apollo.topic.Subscriber;
import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import com.github.saintdan.exception.ConnectionException;
import com.github.saintdan.exception.MQTTException;
import com.github.saintdan.exception.PublishException;
import com.github.saintdan.exception.SubscriberException;
import org.fusesource.mqtt.client.QoS;

/**
 * Implements the
 * {@link Apollo}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/1/15
 * @since JDK1.8
 */
public class ApolloImpl implements Apollo {

    /**
     * Back-end publish.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param config
     *                  ConfigBO {@link ConfigBO}
     * @return
     *                  publish or not
     *
     * @throws ConnectionException
     * @throws PublishException
     * @throws MQTTException
     */
    @Override
    public boolean publish(TopicBO topic, ConfigBO config) throws ConnectionException, PublishException, MQTTException {
        Publisher publisher = new Publisher(config);

        return publisher.pub(topic);
    }

    /**
     * Back-end subscribe.
     *
     * @param topic
     *                  TopicBO {@link TopicBO}
     * @param config
     *                  ConfigBO {@link ConfigBO}
     *
     * @throws SubscriberException
     * @throws MQTTException
     */
    @Override
    public void subscribe(TopicBO topic, ConfigBO config) throws SubscriberException, MQTTException {
        Subscriber subscriber = new Subscriber(config);

        subscriber.sub(topic);
    }



}
