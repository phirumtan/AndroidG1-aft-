package com.gsmarena.firstsample.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsmarena.firstsample.roomdb.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {

        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    //for chapter 12
    private MutableLiveData<List<User>> mUsers;

    public LiveData<List<User>> getUsers() {
        if (mUsers == null) {
            mUsers = new MutableLiveData<>();
            loadUsers();
        }

        return mUsers;
    }

    private void loadUsers() {

        List<User> users = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            User user = new User("phirum" + i, "tan" + i);
            users.add(user);
        }
        mUsers.setValue(users);
    }
}
