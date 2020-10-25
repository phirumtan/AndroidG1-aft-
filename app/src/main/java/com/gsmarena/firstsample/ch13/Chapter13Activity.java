package com.gsmarena.firstsample.ch13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.fragment.LoginFragment;

public class Chapter13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter13);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment(), LoginFragment.TAG)
                .commit();
    }
}
