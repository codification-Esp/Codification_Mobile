package com.aby.roomforall.service.utils;

import feign.Response;



public class ErrorDecoder implements feign.codec.ErrorDecoder {

    private ErrorHandler errorHandler;

    public ErrorDecoder(ErrorHandler handler) {
            errorHandler = handler;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        errorHandler.handle(methodKey, response);
        return null;
    }
}
