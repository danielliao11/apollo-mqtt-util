package com.github.saintdan.exception;

import com.github.saintdan.enums.ErrorType;

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
