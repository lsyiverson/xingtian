package com.lsyiverson.xingtian.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lsyiverson.xingtian.R;
import com.lsyiverson.xingtian.network.RestClient;

public class MainActivity extends AppCompatActivity {

    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restClient = RestClient.getInstance();
    }
}
