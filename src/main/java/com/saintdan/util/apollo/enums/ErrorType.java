package com.saintdan.util.apollo.enums;

/**
 * Apollo mqtt util error types.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/10/15
 * @since JDK1.8
 */
public enum ErrorType implements IntentState {

    // System error.
    SYS0001("System error."),

    // Connection errors.
    CNN0001("Connection error."),
    CNN0010("Blocking connection error."),
    CNN0020("Future connection error."),
    CNN0030("Callback connection error."),

    // Publish errors.
    PUB0001("Publish error."),
    PUB0010("Blocking publish error."),
    PUB0020("Future publish error."),
    PUB0030("Callback publish error."),

    // Subscriber
    SUB0001("Subscriber error."),
    SUB0010("Blocking subscriber error."),
    SUB0020("Future subscriber error."),
    SUB0030("Callback subscriber error."),

    // MQTT
    MQT0001("MQTT error."),

    // Unknown error.
    UNKNOWN("unknown error."),;

    /**
     * Value
     */
    private final String val;

    /**
     * Constructor
     *
     * @param val value
     */
    private ErrorType(String val) {
        this.val = val;
    }

    @Override
    public String value() {
        return this.val;
    }
}
