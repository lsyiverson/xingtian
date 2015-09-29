package com.lsyiverson.xingtian.network.service;

import com.lsyiverson.xingtian.network.model.juhe.MobileResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface JuheService {

    @GET("mobile/get")
    Observable<MobileResponse> getMobileInfo(@Query("phone") String phone, @Query("key") String key);
}
