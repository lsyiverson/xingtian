package com.lsyiverson.xingtian.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lsyiverson.xingtian.BR;
import com.lsyiverson.xingtian.network.model.juhe.MobileInfo;

public class MobileItemViewModel extends BaseObservable {
    private MobileInfo mobileInfo;
    private String mobileNumber;

    @Bindable
    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
        notifyPropertyChanged(BR.mobileInfo);
    }

    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);
    }
}
