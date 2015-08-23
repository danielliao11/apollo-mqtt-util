package com.saintdan.util.apollo.exception;


import com.saintdan.util.apollo.enums.ErrorType;

import java.io.Serializable;

/**
 * @author Liao Wangshu
 * @date 7/26/15
 * @since JDK1.7
 */
public class MQTTException extends SystemException implements Serializable {

    private static final long serialVersionUID = 5775040106874080166L;

    private final static ErrorType ERROR_TYPE = ErrorType.CNN0001;

    public MQTTException(ErrorType msg, Throwable t) {
        super(msg, t);
    }

    public MQTTException(ErrorType msg) {
        super(msg);
    }

    public MQTTException() {
        super(ERROR_TYPE);
    }
}
