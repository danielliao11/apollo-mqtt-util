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

    private static final Apollo apollo = new ApolloImpl();
    private static final Generator generator = new Generator();

    private static final String HOST = "tcp://localhost:61613";
    private static final String SENDER = "sender";
    private static final QoS qos = QoS.AT_LEAST_ONCE;

    @Test
    public void testPBlocking() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Blocking");
        ConfigBO config = generator.configGenerator(HOST, SENDER, SENDER, ApiType.BLOCKING, qos);

        Assert.assertTrue(apollo.publish(topic, config));
    }

    @Test
    public void testPFuture() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Future");
        ConfigBO config = generator.configGenerator(HOST, SENDER, SENDER, ApiType.FUTURE, qos);

        Assert.assertTrue(apollo.publish(topic, config));
    }

    @Test
    public void testPCallback() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Callback");
        ConfigBO config = generator.configGenerator(HOST, SENDER, SENDER, ApiType.CALLBACK, qos);

        Assert.assertTrue(apollo.publish(topic, config));

    }

}
