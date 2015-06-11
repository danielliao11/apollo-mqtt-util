package com.github.saintdan.exception;

import com.github.saintdan.enums.ErrorType;

/**
 * Connection exception.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class ConnectionException extends SystemException {

    private final static ErrorType ERROR_TYPE = ErrorType.CNN0001;


    public ConnectionException(ErrorType msg, Throwable t) {
        super(msg, t);
    }

    public ConnectionException(ErrorType msg) {
        super(msg);
    }

    public ConnectionException() {
        super(ERROR_TYPE);
    }
}
