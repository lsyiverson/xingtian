package com.lsyiverson.xingtian.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.rxbinding.view.RxView;
import com.lsyiverson.xingtian.R;
import com.lsyiverson.xingtian.ui.MainActivityBinding;
import com.lsyiverson.xingtian.ui.viewmodel.MainActivityViewModel;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding mainActivityBinding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new MainActivityViewModel(this);
        mainActivityBinding.setMainActivityVM(viewModel);

        RxView.clicks(mainActivityBinding.queryButton)
            .debounce(500, TimeUnit.MILLISECONDS)
            .map(o -> mainActivityBinding.mobileNumber.getText().toString())
            .subscribe(viewModel::queryMobileNumber);
    }
}
