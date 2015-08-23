package com.saintdan.util.apollo;

import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.enums.ApiType;
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

    public ConfigBO configGenerator(String host,int port, String username, String password, ApiType apiType, QoS qoSType) {
        ConfigBO configBO = new ConfigBO();
        configBO.setHost(host);
        configBO.setPort(port);
        configBO.setUsername(username);
        configBO.setPassword(password);
        configBO.setApiType(apiType);
        configBO.setQoSType(qoSType);

        return configBO;
    }
}
