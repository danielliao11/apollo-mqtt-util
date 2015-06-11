package com.github.saintdan.enums;

/**
 * Api types.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/3/15
 * @since JDK1.8
 */
public enum ApiType implements IntentState{
    BLOCKING("blocking"),
    FUTURE("future"),
    CALLBACK("callback");

    /**
     * Value
     */
    private final String val;

    /**
     * Constructor
     *
     * @param val value
     */
    private ApiType(String val) {
        this.val = val;
    }

    @Override
    public String value() {
        return this.val;
    }
}
