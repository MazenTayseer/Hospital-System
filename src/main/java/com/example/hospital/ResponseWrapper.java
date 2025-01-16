package com.example.hospital;

public class ResponseWrapper<T> {
    private boolean success;
    private T data;
    private String message;

    public ResponseWrapper(T data) {
        this.success = true;
        this.data = data;
        this.message = "Operation successful";
    }

    public ResponseWrapper(String message) {
        this.success = false;
        this.data = null;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
