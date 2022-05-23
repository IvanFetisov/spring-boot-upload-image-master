package com.fetisov.controller;

public class NoteUploadResponce {
    private String message;

    public NoteUploadResponce (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
