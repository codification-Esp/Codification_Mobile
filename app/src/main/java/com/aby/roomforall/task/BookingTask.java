package com.aby.roomforall.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import com.aby.roomforall.Application;
import com.aby.roomforall.event.AsyncTaskEvent;
import com.aby.roomforall.helper.DialogTools;
import com.aby.roomforall.model.*;
import com.aby.roomforall.service.BookingService;
import com.aby.roomforall.service.utils.ErrorDecoder;
import com.aby.roomforall.service.utils.ErrorHandler;
import feign.*;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import java.util.HashMap;


public class BookingTask extends AsyncTask<Object, Void, AsyncTaskResponse> {

    public static final String NAME = BookingTask.class.getName();
    private Application app;
    private AsyncTaskEvent asyncTaskEvent;
    private Context context;

    public BookingTask(Application app, Context context, Fragment fragment) {
        this.app = app;
        this.context = context;
        try {
            this.asyncTaskEvent = (AsyncTaskEvent) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + "must implement AsyncTaskEvent");
        }
    }

    @Override
    protected void onPreExecute() {
        DialogTools.getInstance().ShowProgressDialog(context);
    }

    @Override
    protected AsyncTaskResponse doInBackground(Object... objects) {
        AsyncTaskResponse taskResponse = new AsyncTaskResponse();
        taskResponse.setTaskName(BookingTask.NAME);

        BookingService service = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .errorDecoder(new ErrorDecoder(new ErrorHandler() {
                    @Override
                    public void handle(String methodKey, Response response) {
                        System.out.println("Reason "+response.reason());
                        System.out.println(response.body().toString());
                        System.out.println(response.status());
                        System.out.println(methodKey);
                        System.out.println(response.request().toString());
                    }
                }))
                .options(new Request.Options(5000, 5000))
                .target(BookingService.class, app.getApiConfig().getHost());
        com.aby.roomforall.model.Response response;
        HashMap<String, Object> req = new HashMap<>();
        req.put("request", "search");
        req.put("data", objects[0]);
        try {
            response = new com.aby.roomforall.model.Response();
            response.setCode(200);
            //response = service.search(req);
            //System.out.println(response.getData());
            //taskResponse.setResponse(response);
        } catch (RetryableException e) {
            taskResponse.setHasError(true);
            taskResponse.setError("Failed to connect.");
        }
        return taskResponse;
    }

    @Override
    protected void onPostExecute(AsyncTaskResponse response) {
        DialogTools.getInstance().HideProgressDialog();
        /*if (response.hasError())
            asyncTaskEvent.onError(response);
        else
            asyncTaskEvent.onSuccess(response);*/
        asyncTaskEvent.onSuccess(response);
    }
}
