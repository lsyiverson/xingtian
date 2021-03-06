package com.lsyiverson.xingtian.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lsyiverson.xingtian.BR;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;

public class MainActivityModel extends BaseObservable {
    private MobileInfo mobileInfo;

    @Bindable
    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
        notifyPropertyChanged(BR.mobileInfo);
    }
}
