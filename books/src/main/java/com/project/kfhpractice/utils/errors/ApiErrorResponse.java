package com.project.kfhpractice.utils.errors;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ApiErrorResponse {

    private String error;
    private String message;
    private int statusCode;
    private String path;
    private LocalDateTime timestamp;
    private HashMap<String, String> errors;

    public ApiErrorResponse(String error, String message, int statusCode, String path, LocalDateTime timestamp, HashMap<String, String> errors) {
        this.error = error;
        this.message = message;
        this.statusCode = statusCode;
        this.path = path;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public ApiErrorResponse() {}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
