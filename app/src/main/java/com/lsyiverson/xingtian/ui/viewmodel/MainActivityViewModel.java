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
    private MobileInfo mobileInfo;
    private JuheService juheService;

    public MainActivityViewModel(Context context) {
        juheService = RestClient.getInstance().getJuheService();
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
