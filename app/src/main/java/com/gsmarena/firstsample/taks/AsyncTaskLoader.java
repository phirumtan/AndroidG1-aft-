package com.gsmarena.firstsample.taks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.gsmarena.firstsample.utils.NetworkUtils;

public class AsyncTaskLoader extends androidx.loader.content.AsyncTaskLoader<String> {
    public AsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    /**
     * This method is invoked by the LoaderManager whenever the loader is started
     */
    @Override
    protected void onStartLoading() {
        forceLoad();// Starts the loadInBackground method
    }

    /**
     * Connects to the network and makes the API request on a background thread.
     *
     * @return Returns the raw JSON response from the API call.
     */
    @Override
    public String loadInBackground() {
        return NetworkUtils.getDataFromServer(null);
    }
}
