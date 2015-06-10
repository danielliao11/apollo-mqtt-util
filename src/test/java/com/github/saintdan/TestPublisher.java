package com.github.saintdan;

import com.github.saintdan.apollo.Apollo;
import com.github.saintdan.apollo.ApolloImpl;
import com.github.saintdan.bo.ConfigBO;
import com.github.saintdan.bo.TopicBO;
import com.github.saintdan.enums.ApiType;
import org.fusesource.mqtt.client.QoS;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the
 * {@link com.github.saintdan.apollo.topic.Publisher}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class TestPublisher {

    Apollo apollo = new ApolloImpl();
    private static final String HOST = "tcp://localhost:61613";
    private static final String USERNAME = "sender";
    private static final String PASSWORD = "sender";
    private static final QoS qos = QoS.AT_LEAST_ONCE;

    @Test
    public void testBlocking() throws Exception {
        TopicBO topic = topicGenerator("test", "Hello Blocking");
        ConfigBO config = configGenerator(HOST, USERNAME, PASSWORD, ApiType.BLOCKING, qos);
        boolean published = apollo.publish(topic, config);

        Assert.assertTrue(published);
    }

    @Test
    public void testFuture() throws Exception {
        TopicBO topic = topicGenerator("test", "Hello Future");
        ConfigBO config = configGenerator(HOST, USERNAME, PASSWORD, ApiType.FUTURE, qos);
        boolean published = apollo.publish(topic, config);

        Assert.assertTrue(published);
    }

    @Test
    public void testCallback() throws Exception {
        TopicBO topic = topicGenerator("test", "Hello Callback");
        ConfigBO config = configGenerator(HOST, USERNAME, PASSWORD, ApiType.CALLBACK, qos);
        boolean published = apollo.publish(topic, config);

        Assert.assertTrue(published);

    }

    private TopicBO topicGenerator(String destination, String content) {
        TopicBO topicBO = new TopicBO();
        topicBO.setDestination(destination);
        topicBO.setContent(content);

        return topicBO;
    }

    private ConfigBO configGenerator(String host, String username, String password, ApiType apiType, QoS qoSType) {
        ConfigBO configBO = new ConfigBO();
        configBO.setHost(host);
        configBO.setUsername(username);
        configBO.setPassword(password);
        configBO.setApiType(apiType);
        configBO.setQoSType(qoSType);

        return configBO;
    }
}
