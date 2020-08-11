package com.gsmarena.firstsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.gsmarena.firstsample.roomdb.User;
import com.gsmarena.firstsample.roomdb.UserDatabase;

public class RoomDbActivity extends AppCompatActivity {

    private final String TAG = RoomDbActivity.class.getSimpleName();

    private UserDatabase mUserDatabase;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_db);
        gson = new Gson();

        Button btnInitDb = findViewById(R.id.btn_init_db);
        Button btnInsert = findViewById(R.id.btn_insert);
        Button btnGetData = findViewById(R.id.btn_get_data);
        Button btnUpdate = findViewById(R.id.btn_update);


        btnInitDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mUserDatabase = UserDatabase.getInstance(RoomDbActivity.this);
                    }
                }).start();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User("phirum", "tan");

                        mUserDatabase.mUserDao().insertAll(user);
                    }
                }).start();

            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "all data user from db " + gson.toJson(mUserDatabase.mUserDao().getAll()));
                    }
                }).start();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User("phirum", "tanUpdate");

                        mUserDatabase.mUserDao().update(user);
                    }
                }).start();
            }
        });


    }
}
