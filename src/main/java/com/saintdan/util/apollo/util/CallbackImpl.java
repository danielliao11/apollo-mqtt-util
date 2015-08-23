package com.saintdan.util.apollo.util;

import org.fusesource.mqtt.client.Callback;

/**
 * Implements the
 * {@link Callback}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class CallbackImpl implements Callback{

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onFailure(Throwable value) {
        System.out.println("Failure: " + value.getMessage());
    }
}
