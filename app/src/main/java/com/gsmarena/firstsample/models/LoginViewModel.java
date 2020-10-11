package com.gsmarena.firstsample.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsmarena.firstsample.roomdb.User;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel {

    // Create a LiveData with a String
    public MutableLiveData<List<User>> mUsers;
    public MutableLiveData<User> mUserLogin;

    public LiveData<List<User>> getUsers() {
        if (mUsers == null) {
            mUsers = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return mUsers;
    }

    public void loadUsers() {
        if (mUsers == null)
            mUsers = new MutableLiveData<List<User>>();
        // Do an asynchronous operation to fetch users.
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int j = i + 1;
            User user = new User("phirum" + j, "tan" + j);
            users.add(user);
        }
        mUsers.setValue(users);
    }

    public void saveUserLogin(String fName, String lName) {
        if (mUserLogin == null) {
            mUserLogin = new MutableLiveData<>();
        }
        mUserLogin.setValue(new User(fName, lName));
    }

    public LiveData<User> getLoginData() {
        return mUserLogin;
    }
}
