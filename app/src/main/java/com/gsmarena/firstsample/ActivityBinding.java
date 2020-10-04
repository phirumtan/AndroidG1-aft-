package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gsmarena.firstsample.databinding.ActivityBindingBinding;
import com.gsmarena.firstsample.roomdb.User;

public class ActivityBinding extends AppCompatActivity {

    private ActivityBindingBinding mBindingView;

    /**
     * for more information :
     * https://developer.android.com/topic/libraries/data-binding
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_binding);
        mBindingView = DataBindingUtil.setContentView(this, R.layout.activity_binding);
        User user = new User("phirum", "Tan");
        mBindingView.setUser(user);
        mBindingView.setCallback(new MyUpdateListener());
    }

    public class MyUpdateListener {
        public void updateUser() {
            User user = new User("phirum(Update)", "Tan(update)");
            mBindingView.setUser(user);
        }
    }
}
