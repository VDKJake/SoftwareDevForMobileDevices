package com.example.vdkja.week10;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.timerView);
    }

    public void onClick(View v)
    {
        new TimerTask().execute();
    }

    private class TimerTask extends AsyncTask<Integer, Integer, Void>{
        protected Void doInBackground(Integer... integers)
        {
            try {
                for (int i = 3; i >= 0; i--) {
                    Thread.sleep(1000);
                    Log.i("THREADSTUFF", Integer.toString(i));
                    publishProgress(i);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress)
        {
            status.setText(Integer.toString(progress[0]));
        }
    }
}
