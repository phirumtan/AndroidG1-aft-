package com.gsmarena.firstsample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.gsmarena.firstsample.BuildConfig;
import com.gsmarena.firstsample.R;

/**
 * Broadcast Receiver implementation that delivers a custom Toast
 * message when it receives any of the registered broadcasts
 */
public class CustomReceiver extends BroadcastReceiver {

    //String constant that defines the custom Broadcast Action
    public static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    public CustomReceiver() {
        super();
    }

    /**
     * This method gets called when the Broadcast Receiver receives a broadcast that
     * it is registered for.
     *
     * @param context The context of the application when the broadcast is received.
     * @param intent The broadcast is delivered in the form of an intent which contains
     *               the broadcast action.
     */

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String toastMessage = null;
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = context.getString(R.string.power_connected);
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = context.getString(R.string.power_disconnected);
                break;
            case Intent.ACTION_BATTERY_CHANGED:
                toastMessage = "Battery Changed";
                break;
            case Intent.ACTION_BATTERY_LOW:
                toastMessage = "Battery is low, please charge.";
                break;
            case ACTION_CUSTOM_BROADCAST:
                toastMessage = context.getString(R.string.custom_broadcast_toast);
                break;
        }
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
