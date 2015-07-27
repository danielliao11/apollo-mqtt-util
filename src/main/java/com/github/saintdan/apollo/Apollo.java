package com.github.saintdan.apollo;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import com.github.saintdan.exception.ConnectionException;
import com.github.saintdan.exception.MQTTException;
import com.github.saintdan.exception.PublishException;
import com.github.saintdan.exception.SubscriberException;
import org.fusesource.mqtt.client.QoS;

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
