package com.saintdan.util.apollo.apollo;

import com.saintdan.util.apollo.apollo.topic.Publisher;
import com.saintdan.util.apollo.apollo.topic.Subscriber;
import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.exception.ConnectionException;
import com.saintdan.util.apollo.exception.MQTTException;
import com.saintdan.util.apollo.exception.PublishException;
import com.saintdan.util.apollo.exception.SubscriberException;

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
