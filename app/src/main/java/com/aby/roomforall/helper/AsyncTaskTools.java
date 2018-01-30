package com.aby.roomforall.helper;

import android.os.AsyncTask;


public class AsyncTaskTools {
    public static <P, T extends AsyncTask<P, ?, ?>> void execute(T task) {
        execute(task, (P[]) null);
    }

    public static <P, T extends AsyncTask<P, ?, ?>> void execute(T task, P... params) {
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
    }
}
