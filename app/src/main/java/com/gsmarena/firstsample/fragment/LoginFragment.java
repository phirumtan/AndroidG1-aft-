package com.gsmarena.firstsample.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.databinding.FragmentLoginBinding;
import com.gsmarena.firstsample.models.LoginViewModel;

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getSimpleName();
    private FragmentLoginBinding mViewBinding;
    private LoginViewModel mLoginViewModel;
    private FragmentManager mManager;


    public LoginFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() == null)
            return;

        mManager = getParentFragmentManager();

        mLoginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        mViewBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable edtUsername = mViewBinding.edtUsername.getText();
                Editable edtPassword = mViewBinding.edtPassword.getText();
                if (edtUsername != null && edtPassword != null) {
                    if (edtUsername.length() > 0 && edtPassword.length() > 0) {
                        mLoginViewModel.saveUserLogin(edtUsername.toString(), edtPassword.toString());
                        mManager.beginTransaction().replace(R.id.container, new UserDetailFragment(),
                                UserDetailFragment.TAG).addToBackStack(UserDetailFragment.TAG).commit();
                    } else {
                        Toast.makeText(v.getContext(), "username or password can not empty", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(v.getContext(), "username or password can not empty", Toast.LENGTH_SHORT).show();

                //getParentFragmentManager().beginTransaction().replace(R.id.container, new UserDetailFragment(), UserDetailFragment.TAG).commitAllowingStateLoss();
            }
        });
    }
}
