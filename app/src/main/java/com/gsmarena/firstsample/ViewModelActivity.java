package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gsmarena.firstsample.fragment.LoginFragment;

public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment(), LoginFragment.TAG)
                .commit();
    }
}
