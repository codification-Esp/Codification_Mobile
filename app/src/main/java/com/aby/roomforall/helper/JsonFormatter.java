package com.aby.roomforall.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonFormatter {

    private JsonFormatter() {}

    private static JsonFormatter instance = null;
    private static Gson gson = null;

    public static JsonFormatter getInstance() {
        if (instance == null) {
            synchronized (JsonFormatter.class) {
                if (instance == null) instance = new JsonFormatter();
            }
        }
        return instance;
    }

    public static Gson gson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) gson = (new GsonBuilder()).setPrettyPrinting().create();
            }
        }
        return gson;
    }
}
