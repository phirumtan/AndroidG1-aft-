package com.gsmarena.firstsample.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.gsmarena.firstsample.retrofit2.APIClient;
import com.gsmarena.firstsample.retrofit2.APIUserInterface;
import com.gsmarena.firstsample.retrofit2.item.Datum;
import com.gsmarena.firstsample.retrofit2.item.UserItem;
import com.gsmarena.firstsample.ui.model.ResponseCreateUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {

    private static final String TAG = UsersRepository.class.getSimpleName();
    private static UsersRepository sUsersRepository;

    public static UsersRepository getInstance() {
        if (sUsersRepository == null) {
            sUsersRepository = new UsersRepository();
        }
        return sUsersRepository;
    }

    private APIUserInterface mApiUserInterface;

    public UsersRepository() {
        mApiUserInterface = APIClient.getClient().create(APIUserInterface.class);
    }

    public MutableLiveData<List<Datum>> fetchDataFromServer(String page) {
        final MutableLiveData<List<Datum>> liveData = new MutableLiveData<>();
        Call<UserItem> userItemCall = mApiUserInterface.doGetUserList(page);
        userItemCall.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(@NonNull Call<UserItem> call, @NonNull Response<UserItem> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getData() != null) {
                        liveData.setValue(response.body().getData());
                    }
                } else
                    Log.e(TAG, "response not success " + (response.errorBody() != null ? response.errorBody().toString() : "error with response from server"));
            }

            @Override
            public void onFailure(@NonNull Call<UserItem> call, @NonNull Throwable t) {
                Log.e(TAG, "response not success " + (t.getMessage()));
            }
        });

        return liveData;
    }

    public MutableLiveData<ResponseCreateUser> createUserToServer(String name, String job) {
        final MutableLiveData<ResponseCreateUser> liveData = new MutableLiveData<>();
        Call<ResponseCreateUser> userItemCall = mApiUserInterface.doCreate(name, job);
        userItemCall.enqueue(new Callback<ResponseCreateUser>() {
            @Override
            public void onResponse(Call<ResponseCreateUser> call, Response<ResponseCreateUser> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else
                    Log.e(TAG, "response not success " + (response.errorBody() != null ? response.errorBody().toString() : "error with response from server"));
            }

            @Override
            public void onFailure(Call<ResponseCreateUser> call, Throwable t) {
                Log.e(TAG, "response not success " + (t.getMessage()));
            }
        });
        return liveData;
    }

}
