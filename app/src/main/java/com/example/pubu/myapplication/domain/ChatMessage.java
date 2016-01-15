package com.example.pubu.myapplication.domain;

/**
 * Created by pubu on 2016/1/14.
 */
public class ChatMessage {
    public boolean left;
    public String message;

    public ChatMessage(boolean left, String message) {
        super();
        this.left = left;
        this.message = message;
    }
}
