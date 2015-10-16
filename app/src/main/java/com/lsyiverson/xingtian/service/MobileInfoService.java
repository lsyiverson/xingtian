package com.lsyiverson.xingtian.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.RestClient;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;
import com.lsyiverson.xingtian.network.model.juhe.MobileResponse;
import com.lsyiverson.xingtian.network.service.JuheService;

import rx.android.schedulers.AndroidSchedulers;

public class MobileInfoService extends IntentService {
    public static final String MOBILE = "mobile";

    private JuheService juheService;

    public MobileInfoService() {
        super("MobileInfoService");

        juheService = RestClient.getInstance().getJuheService();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String mobile = intent.getStringExtra(MOBILE);
        Log.d("TAG", "onHandleIntent");

        juheService.getMobileInfo(mobile, ApiEnvironment.Juhe.getApiKey())
            .observeOn(AndroidSchedulers.mainThread())
            .map(MobileResponse::getResult)
            .filter(mobileInfo -> mobileInfo != null)
            .subscribe(this::showMobileInfo);
    }

    private void showMobileInfo(MobileInfo mobileInfo) {
        Toast.makeText(this, mobileInfo.getCompany()
            + " " + mobileInfo.getProvince()
            + " " + mobileInfo.getCity(), Toast.LENGTH_LONG).show();
    }
}
