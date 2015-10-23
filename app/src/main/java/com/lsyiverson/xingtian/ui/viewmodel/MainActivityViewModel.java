package com.lsyiverson.xingtian.ui.viewmodel;

import android.content.Context;

import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.RestClient;
import com.lsyiverson.xingtian.network.model.juhe.MobileResponse;
import com.lsyiverson.xingtian.network.service.JuheService;
import com.lsyiverson.xingtian.ui.model.MainActivityModel;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivityViewModel {
    private final MainActivityModel model;
    private final JuheService juheService;

    public MainActivityViewModel(Context context) {
        juheService = RestClient.getInstance().getJuheService();
        model = new MainActivityModel();
    }

    public MainActivityModel getModel() {
        return model;
    }

    public void queryMobileNumber(String mobileNumber) {
        juheService.getMobileInfo(mobileNumber, ApiEnvironment.Juhe.getApiKey())
            .observeOn(AndroidSchedulers.mainThread())
            .map(MobileResponse::getResult)
            .subscribe(model::setMobileInfo);
    }
}
