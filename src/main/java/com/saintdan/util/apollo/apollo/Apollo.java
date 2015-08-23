package com.saintdan.util.apollo.apollo;

import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.exception.ConnectionException;
import com.saintdan.util.apollo.exception.MQTTException;
import com.saintdan.util.apollo.exception.PublishException;
import com.saintdan.util.apollo.exception.SubscriberException;

/**
 * Interface of apollo_mqtt_util.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 5/28/15
 * @since JDK1.8
 */
public interface Apollo {

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
    boolean publish(TopicBO topic, ConfigBO config) throws ConnectionException, PublishException, MQTTException;

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
    void subscribe(TopicBO topic, ConfigBO config) throws SubscriberException, MQTTException;
}
