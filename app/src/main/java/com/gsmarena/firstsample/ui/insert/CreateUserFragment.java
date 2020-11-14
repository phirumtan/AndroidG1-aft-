package com.gsmarena.firstsample.ui.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.databinding.ActivityRetrofit2Binding;
import com.gsmarena.firstsample.ui.UsersViewModel;
import com.gsmarena.firstsample.ui.model.ResponseCreateUser;

public class CreateUserFragment extends Fragment {

    public static final String TAG = CreateUserFragment.class.getSimpleName();
    private ActivityRetrofit2Binding mViewBinding;
    private UsersViewModel mUsersViewModel;

    public CreateUserFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.activity_retrofit2, container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() == null)
            return;

        mUsersViewModel = new ViewModelProvider(getActivity()).get(UsersViewModel.class);

        mUsersViewModel.getCreateUserLiveData().observe(getActivity(), new Observer<ResponseCreateUser>() {
            @Override
            public void onChanged(ResponseCreateUser responseCreateUser) {
                Toast.makeText(view.getContext(), "show user created data : " +
                        new Gson().toJson(responseCreateUser), Toast.LENGTH_SHORT).show();
            }
        });

        mViewBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mViewBinding.edtUsername.getText() != null ? mViewBinding.edtUsername.getText().toString() : "";
                String job = mViewBinding.edtPassword.getText() != null ? mViewBinding.edtPassword.getText().toString() : "";
                mUsersViewModel.postCreateUser(name, job);
            }
        });

    }
}
