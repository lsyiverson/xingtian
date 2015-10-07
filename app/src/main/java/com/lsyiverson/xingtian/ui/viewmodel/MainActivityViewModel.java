package com.lsyiverson.xingtian.ui.viewmodel;

import android.content.Context;

import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.RestClient;
import com.lsyiverson.xingtian.network.service.JuheService;

public class MainActivityViewModel {
    private String hello;
    private String requestUrl;
    private String requestKey;
    private JuheService juheService;

    public MainActivityViewModel(Context context) {
        hello = context.getPackageName();
        requestUrl = ApiEnvironment.Juhe.getApiBasePath();
        requestKey = ApiEnvironment.Juhe.getApiKey();

        juheService = RestClient.getInstance().getJuheService();
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }
}
