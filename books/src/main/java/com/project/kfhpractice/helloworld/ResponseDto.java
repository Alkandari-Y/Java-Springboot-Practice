package com.project.kfhpractice.helloworld;

public class ResponseDto<T> {
    private T data;
    private Integer statusCode;
    private Boolean success;

    public ResponseDto(T data, Integer statusCode, Boolean success) {
        this.data = data;
        this.statusCode = statusCode;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }
}
