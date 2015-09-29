package com.lsyiverson.xingtian.network;

import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.service.JuheService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RestClient {

    private static RestClient instance;

    private Retrofit.Builder builder;

    private JuheService juheService;

    private RestClient() {
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        createJuheService();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    private void createJuheService() {
        juheService = builder
                .baseUrl(ApiEnvironment.Juhe.getApiBasePath())
                .build().create(JuheService.class);
    }

    public JuheService getJuheService() {
        return juheService;
    }
}
