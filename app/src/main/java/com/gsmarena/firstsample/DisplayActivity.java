package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

public class DisplayActivity extends AppCompatActivity {

    private AppCompatTextView mDisplayMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));

        mDisplayMsg = findViewById(R.id.txtMsg);

        if (getIntent().getExtras() != null) {
            mDisplayMsg.setText(getIntent().getExtras().getString(MainActivity.EXT_KEY_MSG));
        } else {
            mDisplayMsg.setText("something wrong with bundle key");
        }

    }
}
