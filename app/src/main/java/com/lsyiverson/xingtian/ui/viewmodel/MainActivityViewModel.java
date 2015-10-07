package com.lsyiverson.xingtian.ui.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lsyiverson.xingtian.BR;
import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.RestClient;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;
import com.lsyiverson.xingtian.network.model.juhe.MobileResponse;
import com.lsyiverson.xingtian.network.service.JuheService;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivityViewModel extends BaseObservable {
    private String hello;
    private String requestUrl;
    private String requestKey;
    private MobileInfo mobileInfo;
    private JuheService juheService;

    public MainActivityViewModel(Context context) {
        hello = context.getPackageName();
        requestUrl = ApiEnvironment.Juhe.getApiBasePath();
        requestKey = ApiEnvironment.Juhe.getApiKey();

        juheService = RestClient.getInstance().getJuheService();
    }

    @Bindable
    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
        notifyPropertyChanged(BR.hello);
    }

    @Bindable
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        notifyPropertyChanged(BR.requestUrl);
    }

    @Bindable
    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
        notifyPropertyChanged(BR.requestKey);
    }

    @Bindable
    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
        notifyPropertyChanged(BR.mobileInfo);
    }

    public void queryMobileNumber(String mobileNumber) {
        juheService.getMobileInfo(mobileNumber, ApiEnvironment.Juhe.getApiKey())
            .observeOn(AndroidSchedulers.mainThread())
            .map(MobileResponse::getResult)
            .subscribe(this::setMobileInfo);
    }
}
