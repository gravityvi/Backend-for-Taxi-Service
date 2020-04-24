package org.codejudge.sb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {


    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
