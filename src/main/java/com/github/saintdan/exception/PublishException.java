package com.github.saintdan.exception;

import com.github.saintdan.enums.ErrorType;

/**
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class PublishException extends SystemException {
    private final static ErrorType ERROR_TYPE = ErrorType.PUB0001;


    public PublishException(ErrorType msg, Throwable t) {
        super(msg, t);
    }

    public PublishException(ErrorType msg) {
        super(msg);
    }

    public PublishException() {
        super(ERROR_TYPE);
    }
}
