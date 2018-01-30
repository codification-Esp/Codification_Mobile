package com.aby.roomforall.service.utils;

import feign.Response;

public interface ErrorHandler {

    void handle(String methodKey, Response response);
}
