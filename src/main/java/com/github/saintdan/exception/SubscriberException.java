package com.github.saintdan.exception;

import com.github.saintdan.enums.ErrorType;

/**
 * Subscriber exception.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/11/15
 * @since JDK1.8
 */
public class SubscriberException extends SystemException{
    private final static ErrorType ERROR_TYPE = ErrorType.PUB0001;


    public SubscriberException(ErrorType msg, Throwable t) {
        super(msg, t);
    }

    public SubscriberException(ErrorType msg) {
        super(msg);
    }

    public SubscriberException() {
        super(ERROR_TYPE);
    }
}
