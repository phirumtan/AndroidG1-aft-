package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gsmarena.firstsample.databinding.ActivityDataBindingBinding;
import com.gsmarena.firstsample.roomdb.User;

public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        User user = new User("Test", "Binding");

        mBinding.setUser(user);

        /*User user = new User("Test", "Binding");

        TextView firstname = findViewById(R.id.tv_first);
        TextView lastName = findViewById(R.id.tv_last);

        firstname.setText(user.firstName);
        lastName.setText(user.lastName);*/

    }
}
