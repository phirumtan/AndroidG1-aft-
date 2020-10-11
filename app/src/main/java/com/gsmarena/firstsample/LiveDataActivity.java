package com.gsmarena.firstsample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.gsmarena.firstsample.databinding.ActivityLiveDataBinding;
import com.gsmarena.firstsample.models.UserViewModel;
import com.gsmarena.firstsample.roomdb.User;

import java.util.List;

public class LiveDataActivity extends AppCompatActivity {

    private static final String TAG = LiveDataActivity.class.getSimpleName();

    private ActivityLiveDataBinding mDataBinding;
    private UserViewModel mUserViewModel;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_live_data);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);

        this.mGson = new Gson();

        //instance livedata
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        /*Observer<String> observerCurrentName = new Observer<String>() {
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
        });*/

        mUserViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.d(TAG, "user data : " + mGson.toJson(users));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
