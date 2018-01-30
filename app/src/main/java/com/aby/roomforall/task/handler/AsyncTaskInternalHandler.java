package com.aby.roomforall.task.handler;

import com.aby.roomforall.model.AsyncTaskResponse;


public interface AsyncTaskInternalHandler {

    void handle(AsyncTaskResponse response);
    void handleError(AsyncTaskResponse response);
}
