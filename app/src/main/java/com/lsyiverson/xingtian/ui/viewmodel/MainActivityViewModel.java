package com.lsyiverson.xingtian.ui.viewmodel;

import android.content.Context;

import com.google.common.collect.Lists;
import com.lsyiverson.xingtian.constant.ApiEnvironment;
import com.lsyiverson.xingtian.network.RestClient;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;
import com.lsyiverson.xingtian.network.model.juhe.MobileResponse;
import com.lsyiverson.xingtian.network.service.JuheService;
import com.lsyiverson.xingtian.ui.adapter.MobileHistoryAdapter;
import com.lsyiverson.xingtian.ui.model.MainActivityModel;
import com.lsyiverson.xingtian.ui.model.MobileItemModel;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivityViewModel {
    private final MainActivityModel model;
    private final JuheService juheService;
    private List<MobileItemModel> mobileItemModelList;
    private MobileHistoryAdapter adapter;

    public MainActivityViewModel(Context context) {
        juheService = RestClient.getInstance().getJuheService();
        model = new MainActivityModel();

        mobileItemModelList = Lists.newArrayList();
        adapter = new MobileHistoryAdapter(context, mobileItemModelList);
    }

    public MainActivityModel getModel() {
        return model;
    }

    public MobileHistoryAdapter getAdapter() {
        return adapter;
    }

    public void queryMobileNumber(String mobileNumber) {
        juheService.getMobileInfo(mobileNumber, ApiEnvironment.Juhe.getApiKey())
            .observeOn(AndroidSchedulers.mainThread())
            .map(MobileResponse::getResult)
            .doOnNext(mobileInfo -> addMobileQueryRecord(mobileNumber, mobileInfo))
            .subscribe(model::setMobileInfo);
    }

    private void addMobileQueryRecord(String mobileNumber, MobileInfo mobileInfo) {
        mobileItemModelList.add(new MobileItemModel(mobileNumber, mobileInfo));
        adapter.notifyDataSetChanged();
    }
}
