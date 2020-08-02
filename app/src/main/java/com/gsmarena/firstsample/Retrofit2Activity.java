package com.gsmarena.firstsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.gsmarena.firstsample.retrofit2.APIClient;
import com.gsmarena.firstsample.retrofit2.APIUserInterface;
import com.gsmarena.firstsample.retrofit2.item.RegisterItem;
import com.gsmarena.firstsample.retrofit2.item.ResponseRegister;
import com.gsmarena.firstsample.retrofit2.item.UserItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit2Activity extends AppCompatActivity {

    private static final String TAG = Retrofit2Activity.class.getSimpleName();
    private Button mBtnGetData;

    private Button mBtnLogin, mBtnRegister;

    private TextInputEditText mEdtUsername, mEdtPass;

    private APIUserInterface mApiUserInterface;

    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        mBtnGetData = findViewById(R.id.btn_get_user_list);

        mBtnLogin = findViewById(R.id.btn_login);
        mBtnRegister = findViewById(R.id.btn_register);

        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPass = findViewById(R.id.edt_password);

        mApiUserInterface = APIClient.getClient().create(APIUserInterface.class);

        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UserItem> getUserList = mApiUserInterface.doGetUserList("2");
                getUserList.enqueue(new Callback<UserItem>() {
                    @Override
                    public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                        if (response.isSuccessful()) {
                            UserItem userItem = response.body();
                            Log.d(TAG, "Handle response from server " + mGson.toJson(userItem));
                        } else {
                            Log.e(TAG, "error response!!!!");
                        }

                    }

                    @Override
                    public void onFailure(Call<UserItem> call, Throwable t) {

                        Log.e(TAG, "error with " + t.getLocalizedMessage());
                    }
                });
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterItem item = new RegisterItem();
                item.setEmail("peter@klaven");
                item.setPassword("");
                Call<Object> postToLogin = mApiUserInterface.doLogin(item);
                postToLogin.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "Handle login response from server " + mGson.toJson(response.body()));
                        } else {
                            Log.e(TAG, "error response!!!!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e(TAG, "error with " + t.getLocalizedMessage());
                    }
                });

            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseRegister> postRegisterToServer = mApiUserInterface.doRegister(mEdtUsername.getText().toString(), mEdtPass.getText().toString());
                postRegisterToServer.enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "Handle register response from server " + mGson.toJson(response.body()));
                        } else {
                            Log.e(TAG, "error response!!!!");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {

                        Log.e(TAG, "error with " + t.getLocalizedMessage());
                    }
                });
            }
        });
    }
}
