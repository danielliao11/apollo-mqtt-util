package com.saintdan.util.apollo;

import com.saintdan.util.apollo.apollo.Apollo;
import com.saintdan.util.apollo.apollo.ApolloImpl;
import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.enums.ApiType;
import com.saintdan.util.apollo.apollo.topic.Publisher;
import org.fusesource.mqtt.client.QoS;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the
 * {@link Publisher}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class TestPublisher {

    private static final Apollo apollo = new ApolloImpl();
    private static final Generator generator = new Generator();

    private static final String HOST = "localhost";
    private static final int PORT = 61613;
    private static final String SENDER = "sender";
    private static final QoS qos = QoS.AT_LEAST_ONCE;

    @Test
    public void testPBlocking() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Blocking");
        ConfigBO config = generator.configGenerator(HOST, PORT, SENDER, SENDER, ApiType.BLOCKING, qos);

        Assert.assertTrue(apollo.publish(topic, config));
    }

    @Test
    public void testPFuture() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Future");
        ConfigBO config = generator.configGenerator(HOST, PORT, SENDER, SENDER, ApiType.FUTURE, qos);

        Assert.assertTrue(apollo.publish(topic, config));
    }

    @Test
    public void testPCallback() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "Hello Callback");
        ConfigBO config = generator.configGenerator(HOST, PORT, SENDER, SENDER, ApiType.CALLBACK, qos);

        Assert.assertTrue(apollo.publish(topic, config));

    }

}
