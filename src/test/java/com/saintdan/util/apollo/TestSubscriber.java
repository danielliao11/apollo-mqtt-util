package com.saintdan.util.apollo;

import com.saintdan.util.apollo.apollo.Apollo;
import com.saintdan.util.apollo.apollo.ApolloImpl;
import com.saintdan.util.apollo.bo.ConfigBO;
import com.saintdan.util.apollo.bo.TopicBO;
import com.saintdan.util.apollo.enums.ApiType;
import org.fusesource.mqtt.client.QoS;
import org.junit.Test;

/**
 * Test the
 * {@link TestSubscriber}
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/11/15
 * @since JDK1.8
 */
public class TestSubscriber {
    private static final Apollo apollo = new ApolloImpl();
    private static final Generator generator = new Generator();

    private static final String HOST = "localhost";
    private static final int PORT = 61613;
    private static final String RECEIVER = "receiver";
    private static final QoS qos = QoS.AT_LEAST_ONCE;

    @Test
    public void testSCallback() throws Exception {
        TopicBO topic = generator.topicGenerator("test", "");
        ConfigBO config = generator.configGenerator(HOST, PORT, RECEIVER, RECEIVER, ApiType.CALLBACK, qos);

        apollo.subscribe(topic, config);
    }
}
