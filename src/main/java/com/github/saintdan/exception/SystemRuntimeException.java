package com.github.saintdan.exception;

import com.github.saintdan.enums.ErrorType;

/**
 * Abstract superclass for all runtime exceptions related to an
 * {@link SystemRuntimeException} object being invalid for whatever reason.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public class SystemRuntimeException extends RuntimeException{
    /**
     * Error Type
     */
    private ErrorType errorType;

    /**
     * Constructs an {@code ServiceRuntimeException} with the specified message and root cause.
     *
     * @param msg the {@link ErrorType}
     * @param t the root cause
     */
    public SystemRuntimeException(ErrorType msg, Throwable t) {

        super(msg.name() + ": " + msg.value(), t);
        this.errorType=msg;
    }

    /**
     * Constructs an {@code ServiceRuntimeException} with the specified message and no root cause.
     *
     * @param msg the {@link ErrorType}
     */
    public SystemRuntimeException(ErrorType msg) {

        super(msg.name() + ": " + msg.value());
        this.errorType=msg;
    }

    /**
     * Constructs an {@code SystemRuntimeException}
     */
    public SystemRuntimeException(){
        this(ErrorType.SYS0001);
    }


    private ErrorType obtainErrorType(){
        if(errorType==null){
            return ErrorType.SYS0001;
        }
        return errorType;

    }

    /**
     * Get error type
     * @return {@link ErrorType}
     */
    public ErrorType getErrorType(){
        return obtainErrorType();
    }

    /**
     * Get error code
     * @return error code
     */
    public String getErrorCode() {
        return obtainErrorType().name();
    }

    /**
     * Get error msg
     * @return error msg
     */
    public String getErrorMsg() {
        return obtainErrorType().value();
    }
}
