package com.gsmarena.firstsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;

import com.gsmarena.firstsample.dialog.DatePickerFragment;
import com.gsmarena.firstsample.dialog.TimePickerFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName(); //"MainActivity"


    public static final String EXT_KEY_MSG = "ext_key_msg";
    private static final String LOG_TAG = "activityLife";

    private AppCompatEditText mEdtMsg;
    private AppCompatButton mBtnSend;

    private AppCompatTextView mText;

    private AppCompatButton mBtnIntent;
    private AppCompatButton mBtnOpenUIKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);

        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(true);*/


        mEdtMsg = findViewById(R.id.edt_enter_msg);

        mBtnSend = findViewById(R.id.btn_send);

        mText = findViewById(R.id.text);

        mBtnIntent = findViewById(R.id.btn_implicit_intent);

        mBtnOpenUIKit = findViewById(R.id.btn_open_ui_kit);


        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayActivity.class);
                intent.putExtra(EXT_KEY_MSG, mEdtMsg.getText().toString());
                startActivity(intent);
            }
        });

        mBtnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri webpage = Uri.parse("https://www.google.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                // Find an activity to hand the intent and start that activity.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.e("ImplicitIntents", "Can't handle this intent!");
                }*/

                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Share with: ")
                        .setText("hello share text content!!!")
                        .startChooser();


            }
        });

        mBtnOpenUIKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UIKitActivity.class);
                startActivity(intent);
            }
        });

        AppCompatButton btnOpenUIKit1 = findViewById(R.id.btn_open_ui_kit_1);

        btnOpenUIKit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SpinnerSwitchDateTimeActivity.class);
                startActivity(intent);
            }
        });

        AppCompatButton btn_show_date = findViewById(R.id.btn_show_date);
        btn_show_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment().show(getSupportFragmentManager(), "DatePicker");
            }
        });

        AppCompatButton btn_show_time = findViewById(R.id.btn_show_time);
        btn_show_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerFragment().show(getSupportFragmentManager(), "TimePicker");
            }
        });

        AppCompatButton btn_show_rcv = findViewById(R.id.btn_show_rcv);
        btn_show_rcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RecyclerviewActivity.class));
            }
        });

        AppCompatButton btn_show_pager = findViewById(R.id.btn_show_pager);
        btn_show_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ViewPagerActivity.class));
            }
        });

        AppCompatButton btn_show_webview = findViewById(R.id.btn_show_webview);
        btn_show_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WebviewActivity.class));
            }
        });

        AppCompatButton btn_show_asynctask = findViewById(R.id.btn_show_asynctask);
        btn_show_asynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AsyncTaskActivity.class));
            }
        });

        AppCompatButton btn_show_asynctask_loader = findViewById(R.id.btn_show_asynctask_loader);
        btn_show_asynctask_loader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AsyncTaskLoaderActivity.class));
            }
        });

        AppCompatButton btn_show_retrofit2 = findViewById(R.id.btn_show_retrofit2);
        btn_show_retrofit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Retrofit2Activity.class));
            }
        });

        AppCompatButton btn_show_broadcast = findViewById(R.id.btn_show_broadcast);
        btn_show_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), BroadcastActivity.class));
            }
        });


        AppCompatButton btn_show_notification = findViewById(R.id.btn_show_notification);
        btn_show_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), NotificationActivity.class));
            }
        });

        AppCompatButton btn_show_share_pref = findViewById(R.id.btn_show_share_pref);
        btn_show_share_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SharePrefActivity.class));
            }
        });

        AppCompatButton btn_show_room_db = findViewById(R.id.btn_show_room_db);
        btn_show_room_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RoomDbActivity.class));
            }
        });

        AppCompatButton btn_show_data_binding = findViewById(R.id.btn_show_data_binding);
        btn_show_data_binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ActivityBinding.class));
            }
        });

        AppCompatButton btn_show_live_data = findViewById(R.id.btn_show_live_data);
        btn_show_live_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LiveDataActivity.class));
            }
        });

        AppCompatButton btn_show_view_model = findViewById(R.id.btn_show_view_model);
        btn_show_view_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ViewModelActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid:
                Toast.makeText(this, R.string.title_grid, Toast.LENGTH_LONG).show();
                break;
            case R.id.action_list:
                Toast.makeText(this, "click list", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "click settings", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

}
