package com.gsmarena.firstsample;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationActivity extends AppCompatActivity {

    private Button mBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mBtnShow = findViewById(R.id.btn_show);

        Intent intent = new Intent(this, Retrofit2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "api28Up")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notify Title")
                .setContentText("Notify Content")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_view_list, "open", pendingIntent)
                .addAction(R.drawable.ic_view_list, "close", pendingIntent)
                .addAction(R.drawable.ic_view_list, "cancel", pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(0, builder.build());
            }
        });

    }
}
