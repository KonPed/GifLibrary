package com.konped;

public class FlashMessage {
    private String message;
    private Status status;

    public enum Status {
        SUCCESS("1"), FAILURE("2");
        String code;

        Status(String code) {
            this.code = code;
        }
    }

    public FlashMessage(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}
