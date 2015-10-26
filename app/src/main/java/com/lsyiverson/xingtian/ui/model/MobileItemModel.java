package com.lsyiverson.xingtian.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lsyiverson.xingtian.BR;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;

public class MobileItemModel extends BaseObservable {
    private String mobileNumber;
    private MobileInfo mobileInfo;

    public MobileItemModel(String mobileNumber, MobileInfo mobileInfo) {
        this.mobileNumber = mobileNumber;
        this.mobileInfo = mobileInfo;
    }

    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);
    }

    @Bindable
    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
        notifyPropertyChanged(BR.mobileInfo);
    }
}
