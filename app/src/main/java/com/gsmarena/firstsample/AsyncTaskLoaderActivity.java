package com.gsmarena.firstsample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.gsmarena.firstsample.taks.AsyncTaskLoader;

import org.json.JSONObject;

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText mEdtUsername;
    private Button mBtnGetData;
    private View mViewProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);
        mEdtUsername = findViewById(R.id.edt_username);
        mViewProgress = findViewById(R.id.view_progress);
        mBtnGetData = findViewById(R.id.btn_get_data);
        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the keyboard when the button is pushed.
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null) {

                    inputManager.hideSoftInputFromWindow(mEdtUsername.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
                // Check the status of the network connection.
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null)
                    networkInfo = connMgr.getActiveNetworkInfo();
                // If the network is active and the search field is not empty,
                // add the search term to the arguments Bundle and start the loader.
                if (networkInfo != null && networkInfo.isConnected()) {
                    mViewProgress.setVisibility(View.VISIBLE);
                    getSupportLoaderManager().restartLoader(0, null, AsyncTaskLoaderActivity.this);
                }
            }
        });

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        mViewProgress.setVisibility(View.GONE);
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.optBoolean("status")) {
                JSONObject responseObject = jsonObject.optJSONObject("response");
                String username = responseObject.optString("fullname");
                if (!TextUtils.isEmpty(username)) {
                    mEdtUsername.setText(username);
                } else {
                    mEdtUsername.setText("can not get username from server");
                }
            } else
                mEdtUsername.setText("something wrong with return json");
        } catch (Exception e){
            mEdtUsername.setText("error with case object from server");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}