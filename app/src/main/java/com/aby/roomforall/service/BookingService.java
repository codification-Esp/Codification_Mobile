package com.aby.roomforall.service;

import com.aby.roomforall.dto.Trip;
import com.aby.roomforall.model.Response;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.HashMap;
import java.util.List;


@Headers("Accept: application/json")
public interface BookingService {

    @RequestLine("POST /api/booking")
    @Headers({"Content-Type: application/json"})
    Response<List<Trip>> search(HashMap<String, Object> request);
}
