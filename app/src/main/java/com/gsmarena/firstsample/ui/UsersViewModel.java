package com.gsmarena.firstsample.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsmarena.firstsample.retrofit2.item.Datum;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {

    private MutableLiveData<List<Datum>> mMutableUsers;
    private UsersRepository mUsersRepository;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        mUsersRepository = new UsersRepository();
        mMutableUsers = new MutableLiveData<>();
    }

    public void getUserByPage(String page) {
        MutableLiveData<List<Datum>> users = mUsersRepository.fetchDataFromServer(page);
        mMutableUsers.setValue(users.getValue());
    }

    public LiveData<List<Datum>> getUserRecords() {
        return mMutableUsers;
    }
}
