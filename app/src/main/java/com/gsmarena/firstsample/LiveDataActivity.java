package com.gsmarena.firstsample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gsmarena.firstsample.databinding.ActivityLiveDataBinding;
import com.gsmarena.firstsample.models.UserViewModel;

public class LiveDataActivity extends AppCompatActivity {

    private ActivityLiveDataBinding mDataBinding;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_live_data);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);

        //instance livedata
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Observer<String> observerCurrentName = new Observer<String>() {
            @Override
            public void onChanged(String name) {
                mDataBinding.tvName.setText(name);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mUserViewModel.getCurrentName().observe(this, observerCurrentName);

        mDataBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserViewModel.getCurrentName().setValue("Phirum Tan");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
