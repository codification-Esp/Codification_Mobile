package com.aby.roomforall;

import android.content.Context;
import com.aby.roomforall.config.APIConfig;


public class Application extends android.app.Application {

    public static final String TAG = "TukkiApp";
    private Context context;
    private APIConfig apiConfig;
    private String access_token;

    public void prepare(Context context) {
        apiConfig = new APIConfig(
                getResources().getString(R.string.client_id),
                getResources().getString(R.string.client_secret),
                getResources().getString(R.string.host)
        );
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public APIConfig getApiConfig() {
        return apiConfig;
    }
}