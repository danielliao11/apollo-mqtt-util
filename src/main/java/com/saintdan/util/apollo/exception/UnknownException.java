package com.saintdan.util.apollo.exception;

import com.saintdan.util.apollo.enums.ErrorType;

/**
 * Unknown exception.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class UnknownException extends SystemRuntimeException{
    private final static ErrorType ERROR_TYPE=ErrorType.UNKNOWN;

    public UnknownException() {
        super(ERROR_TYPE);
    }
}
