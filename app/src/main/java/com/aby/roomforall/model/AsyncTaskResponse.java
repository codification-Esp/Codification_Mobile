package com.aby.roomforall.model;

import java.io.Serializable;


public class AsyncTaskResponse implements Serializable {

    private String taskName;
    private boolean hasError = false;
    private String error;
    private Response response;

    public AsyncTaskResponse() {
    }

    public AsyncTaskResponse(String taskName, boolean hasError, String error, Response response) {
        this.taskName = taskName;
        this.hasError = hasError;
        this.error = error;
        this.response = response;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
