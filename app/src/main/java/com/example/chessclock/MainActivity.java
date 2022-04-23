package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textTimeWhite;
    TextView textTimeBlack;

    FrameLayout frameWhiteContainer;
    FrameLayout frameBlackContainer;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 60000; // 10mins

    private boolean whiteTimeRunning;
    private boolean blackTimeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        textTimeWhite = findViewById(R.id.textTimeWhite);
        textTimeBlack = findViewById(R.id.textTimeBlack);

        frameWhiteContainer = findViewById(R.id.frameWhiteContainer);
        frameBlackContainer = findViewById(R.id.frameBlackContainer);

        frameWhiteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopWhite();
            }
        });
    }

    public void startStopWhite()
    {
        if (whiteTimeRunning)
        {
            stopWhiteTimer();
        }
        else
        {
            startWhiteTimer();
        }
    }

    private void stopWhiteTimer() {
        countDownTimer.cancel();
        whiteTimeRunning = false;
    }

    private void startWhiteTimer() {

        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateWhiteTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        whiteTimeRunning = true;
    }

    private void updateWhiteTimer() {
        int hours = (int) timeLeftInMilliseconds / 3600000;
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeft;

        timeLeft = "" + hours;
        timeLeft += ":";
        timeLeft += "" + minutes;
        timeLeft += ".";

        if (seconds < 10) timeLeft += "0"; // always get 2 digits for seconds
        timeLeft += seconds;

        textTimeWhite.setText(timeLeft);
    }
}