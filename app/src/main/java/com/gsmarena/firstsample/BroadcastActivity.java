package com.gsmarena.firstsample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.gsmarena.firstsample.receiver.CustomReceiver;

public class BroadcastActivity extends AppCompatActivity {

    CustomReceiver mReceiver = new CustomReceiver();
    private ComponentName mReceiverComponentName;
    private PackageManager mPackageManager;
    private ToggleButton mTgbSwitchReceive;
    private Button mBtnSendCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mTgbSwitchReceive = findViewById(R.id.tgb_switch_receive);
        mBtnSendCustom = findViewById(R.id.btn_send_broad);

        //Get the PackageManager and ComponentName so you can toggle to broadcast receiver.
        mReceiverComponentName = new ComponentName(this, CustomReceiver.class);
        mPackageManager = getPackageManager();
        //Use LocalBroadcastManager so that the broadcast is not received by other applications.
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(CustomReceiver.ACTION_CUSTOM_BROADCAST));

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_grid)
                .setContentTitle("textTitle")
                .setContentText("textContent")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(23, builder.build());

        //onClick method for the button
        mBtnSendCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomBroadcast();
            }
        });

        mTgbSwitchReceive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LocalBroadcastManager.getInstance(buttonView.getContext()).registerReceiver
                            (mReceiver, new IntentFilter(CustomReceiver.ACTION_CUSTOM_BROADCAST));
                } else {
                    LocalBroadcastManager.getInstance(buttonView.getContext()).unregisterReceiver(mReceiver);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        //Enable the broadcast receiver when the app is visible
        mPackageManager.setComponentEnabledSetting
                (mReceiverComponentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
        super.onStart();
    }

    @Override
    protected void onStop() {
        //Disable the broadcast receiver when the app is visible
        mPackageManager.setComponentEnabledSetting
                (mReceiverComponentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
        super.onStop();
    }

    /**
     * Unregister the broadcast receiver when the app is destroyed. This only unregisters it from
     * the custom broadcast, it is still responds to system events defined in AndroidManifest.xml
     */
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    /**
     * OnClick method that sends the custom broadcast using the LocalBroadcastManager
     */
    private void sendCustomBroadcast() {
        Intent customBroadcastIntent = new Intent(CustomReceiver.ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }


}
