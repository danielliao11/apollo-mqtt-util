package com.github.saintdan.bo;

/**
 * Topic BO.
 * @author Liao Yifan
 * @date 5/28/15
 * @since JDK1.8
 */
public class TopicBO {

    private String destination; // Topic name and path
    private String content; // Message content

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
