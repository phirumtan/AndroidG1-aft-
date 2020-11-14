package com.gsmarena.firstsample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gsmarena.firstsample.ui.activities.HomeScreenActivity;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.databinding.FragmentLoginBinding;
import com.gsmarena.firstsample.models.LoginViewModel;
import com.gsmarena.firstsample.retrofit2.APIClient;
import com.gsmarena.firstsample.retrofit2.APIUserInterface;
import com.gsmarena.firstsample.retrofit2.item.RegisterItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getSimpleName();
    private FragmentLoginBinding mViewBinding;
    private LoginViewModel mLoginViewModel;
    private FragmentManager mManager;

    private APIUserInterface mApiUserInterface;
    private Gson mGson;


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

        mGson = new Gson();
        mApiUserInterface = APIClient.getClient().create(APIUserInterface.class);

        mManager = getParentFragmentManager();

        mLoginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        /*mViewBinding.edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count <= 8) {
                    mViewBinding.edtUsername.setError("email not valid");
                } else
                    mViewBinding.edtUsername.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        mViewBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Editable edtUsername = mViewBinding.edtUsername.getText();
                Editable edtPassword = mViewBinding.edtPassword.getText();
                if (edtUsername != null && edtPassword != null) {
                    if (edtUsername.length() > 0 && edtPassword.length() > 0) {

                        if (edtUsername.length() > 30) {
                            mViewBinding.txtInputUsername.setError("Error...");
                            return;
                        }

                        mViewBinding.txtInputUsername.setError(null);

                        mViewBinding.viewLoading.setVisibility(View.VISIBLE);

                        RegisterItem item = new RegisterItem();
                        item.setEmail(edtUsername.toString());
                        item.setPassword(edtPassword.toString());
                        Call<Object> postToLogin = mApiUserInterface.doLogin(item);
                        postToLogin.enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                mViewBinding.viewLoading.setVisibility(View.GONE);
                                if (response.isSuccessful()) {
                                    JsonObject tokenObj = (JsonObject) mGson.toJsonTree(response.body());
                                    String token = tokenObj.get("token").getAsString();
                                    if (!TextUtils.isEmpty(token)) {
                                        Toast.makeText(v.getContext(), "Login successful!!!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(v.getContext(), HomeScreenActivity.class));
                                        Log.d(TAG, "Handle login response from server " + mGson.toJson(response.body()));
                                    }

                                } else {
                                    Toast.makeText(v.getContext(), "error : " + mGson.toJson(response.errorBody()), Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "error response!!!!");
                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                mViewBinding.viewLoading.setVisibility(View.VISIBLE);
                                Log.e(TAG, "error with " + t.getLocalizedMessage());
                            }
                        });

                        /*mLoginViewModel.saveUserLogin(edtUsername.toString(), edtPassword.toString());
                        mManager.beginTransaction().replace(R.id.container, new UserDetailFragment(),
                                UserDetailFragment.TAG).addToBackStack(UserDetailFragment.TAG).commit();*/
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
