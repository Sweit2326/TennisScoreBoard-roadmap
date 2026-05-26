package com.roadmap.fourth.exception;

public class BAD_REQUEST extends RuntimeException {
    public BAD_REQUEST(String message) {
        super(message);
    }
}
