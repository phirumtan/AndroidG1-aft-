package com.gsmarena.firstsample.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.databinding.FragmentUserDetailBinding;
import com.gsmarena.firstsample.models.LoginViewModel;
import com.gsmarena.firstsample.roomdb.User;

import java.util.List;

public class UserDetailFragment extends Fragment {

    public static final String TAG = UserDetailFragment.class.getSimpleName();

    private FragmentUserDetailBinding mViewBinding;
    private Gson mGson;

    public UserDetailFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false);
        return this.mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() == null)
            return;
        this.mGson = new Gson();

        final LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        viewModel.getUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.d(TAG, "user from livedata " + mGson.toJson(users));
            }
        });

        viewModel.getLoginData().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.d(TAG, "user from loginfragment " + mGson.toJson(user));
            }
        });
    }
}
