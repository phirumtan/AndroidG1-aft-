package com.gsmarena.firstsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Random;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton mBtnStartTask;
    private TextView mLabel;
    private LinearLayout mViewPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        mBtnStartTask = findViewById(R.id.btn_start_task);
        mLabel = findViewById(R.id.txt_label);
        mViewPro = findViewById(R.id.view_progress);

        mBtnStartTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_task: {
                new StaticAsync(mLabel).execute();
                break;
            }
        }
    }

    public class StaticAsync extends AsyncTask<Void, Void, String> {
        // The TextView where we will show results
        private TextView mTextView;

        // Constructor that provides a reference to the TextView from the MainActivity
        public StaticAsync(TextView tv) {
            mTextView = tv;
        }

        @Override
        protected void onPreExecute() {
            mViewPro.setVisibility(View.VISIBLE);
        }

        /**
         * Runs on the background thread.
         *
         * @param voids No parameters in this use case.
         * @return Returns the string including the amount of time that
         * the background thread slept.
         */


        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);
            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 2000;
            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        /**
         * Does something with the result on the UI thread; in this case
         * updates the TextView.
         */
        @Override
        protected void onPostExecute(String result) {
            mViewPro.setVisibility(View.GONE);
            mTextView.setText(result);
        }

    }

}
