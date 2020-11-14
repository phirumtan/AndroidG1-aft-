package com.gsmarena.firstsample.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.gsmarena.firstsample.retrofit2.item.Datum;
import com.gsmarena.firstsample.ui.model.ResponseCreateUser;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {

    private LiveData<List<Datum>> mUserLiveData;
    private UsersRepository mUsersRepository;
    private MediatorLiveData<List<Datum>> mMediatorLiveData;

    private MediatorLiveData<ResponseCreateUser> mMediatorCreateUser;
    private LiveData<ResponseCreateUser> mCreateUserLiveData;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        mUsersRepository = UsersRepository.getInstance();
        mUserLiveData = new MediatorLiveData<>();
        mMediatorLiveData = new MediatorLiveData<>();
        mUserLiveData = mMediatorLiveData;
        mMediatorCreateUser = new MediatorLiveData<>();
        mCreateUserLiveData = new MediatorLiveData<>();
        mCreateUserLiveData = mMediatorCreateUser;
    }

    public void getUserByPage(String page) {
        mMediatorLiveData.addSource(mUsersRepository.fetchDataFromServer(page), new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {
                mMediatorLiveData.setValue(data);
            }
        });
    }

    public LiveData<List<Datum>> getUserRecords() {
        return mUserLiveData;
    }

    public void postCreateUser(String name, String job) {
        mMediatorCreateUser.addSource(mUsersRepository.createUserToServer(name, job), new Observer<ResponseCreateUser>() {
            @Override
            public void onChanged(ResponseCreateUser responseCreateUser) {
                mMediatorCreateUser.setValue(responseCreateUser);
            }
        });
    }

    public LiveData<ResponseCreateUser> getCreateUserLiveData() {
        return mCreateUserLiveData;
    }
}
