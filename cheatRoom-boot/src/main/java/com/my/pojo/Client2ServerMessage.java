package com.my.pojo;

public class Client2ServerMessage {

    private String context;

    public Client2ServerMessage(String context) {
        this.context = context;
    }

    public Client2ServerMessage() {
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
