package com.github.saintdan.apollo;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
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
     * Publish.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param config    ConfigBO {@link ConfigBO}
     * @return boolean  isSuccess
     */
    public boolean publish(TopicBO topic, ConfigBO config);

    /**
     * Subscribe.
     *
     * @param topic     TopicBO {@link TopicBO}
     * @param config    ConfigBO {@link ConfigBO}
     */
    public void subscribe(TopicBO topic, ConfigBO config);
}
