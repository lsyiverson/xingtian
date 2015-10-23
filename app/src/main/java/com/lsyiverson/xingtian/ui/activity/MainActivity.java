package com.lsyiverson.xingtian.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.lsyiverson.xingtian.R;
import com.lsyiverson.xingtian.ui.MainActivityBinding;
import com.lsyiverson.xingtian.ui.viewmodel.MainActivityViewModel;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import java8.util.stream.IntStreams;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainActivityViewModel viewModel = new MainActivityViewModel(this);
        mainActivityBinding.setMainActivityModel(viewModel.getModel());

        RxView.clicks(mainActivityBinding.queryButton)
            .debounce(500, TimeUnit.MILLISECONDS)
            .map(o -> mainActivityBinding.mobileNumber.getText().toString())
            .subscribe(viewModel::queryMobileNumber);

        int hasReadPhoneStatePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (hasReadPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                Collections.singletonList(Manifest.permission.READ_PHONE_STATE).toArray(new String[1]),
                REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        IntStreams.range(0, permissions.length)
            .filter(i -> Manifest.permission.READ_PHONE_STATE.equals(permissions[i])
                && grantResults[i] != PackageManager.PERMISSION_GRANTED)
            .forEach(i -> Toast.makeText(MainActivity.this, R.string.phone_permission_toast, Toast.LENGTH_SHORT).show());
    }
}
