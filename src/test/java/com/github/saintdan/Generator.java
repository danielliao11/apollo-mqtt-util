package com.github.saintdan;

import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import org.fusesource.mqtt.client.QoS;

/**
 * Generator.
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/11/15
 * @since JDK1.8
 */
public class Generator {
    public TopicBO topicGenerator(String destination, String content) {
        TopicBO topicBO = new TopicBO();
        topicBO.setDestination(destination);
        topicBO.setContent(content);

        return topicBO;
    }

    public ConfigBO configGenerator(String host, String username, String password, ApiType apiType, QoS qoSType) {
        ConfigBO configBO = new ConfigBO();
        configBO.setHost(host);
        configBO.setUsername(username);
        configBO.setPassword(password);
        configBO.setApiType(apiType);
        configBO.setQoSType(qoSType);

        return configBO;
    }
}
