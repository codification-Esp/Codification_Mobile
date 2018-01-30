package com.aby.roomforall.event;

import com.aby.roomforall.model.AsyncTaskResponse;



public interface AsyncTaskEvent {
    void onSuccess(AsyncTaskResponse response);
    void onProgress(Integer... progress);
    void onError(AsyncTaskResponse response);
    void onCanceled();
}
