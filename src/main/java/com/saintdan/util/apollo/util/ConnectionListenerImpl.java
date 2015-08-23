package com.saintdan.util.apollo.util;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Listener;

/**
 * Implements the
 * {@link ConnectionListenerImpl}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/11/15
 * @since JDK1.8
 */
public class ConnectionListenerImpl implements Listener{

    @Override
    public void onConnected() {
        System.out.println("Connected.");
    }

    @Override
    public void onDisconnected() {
        System.out.println("Disconnected.");
    }

    @Override
    public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
    }

    @Override
    public void onFailure(Throwable value) {
        System.out.println("Failure: "  +value.getMessage());
    }
}
