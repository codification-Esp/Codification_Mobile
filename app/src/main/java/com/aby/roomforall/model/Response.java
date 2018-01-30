package com.aby.roomforall.model;

import java.io.Serializable;


public class Response<T> implements Serializable {
    private int code;
    private T data;
    private Object details;

    public Response() {
    }

    public Response(int code, T data, Object details) {
        this.code = code;
        this.data = data;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
