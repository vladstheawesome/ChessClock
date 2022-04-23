package com.example.chessclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textTimeWhite;
    TextView textTimeBlack;

    FrameLayout frameWhiteContainer;
    FrameLayout frameBlackContainer;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 1800000; // 30mins

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

        // Click event for White
        frameWhiteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopWhite();
            }
        });

        // Click event for Black
        frameBlackContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopBlack();
            }
        });

        updateWhiteTimer();
        updateBlackTimer();
    }

    public void startStopWhite()
    {
        if (whiteTimeRunning)
        {
            stopWhiteTimer();
            startBlackTimer();
        }
        else
        {
            startWhiteTimer();
            stopBlackTimer();
        }
    }

    public void startStopBlack()
    {
        if (blackTimeRunning)
        {
            stopBlackTimer();
            startWhiteTimer();
        }
        else
        {
            startBlackTimer();
            stopWhiteTimer();
        }
    }

    private void stopWhiteTimer() {
        countDownTimer.cancel();
        whiteTimeRunning = false;
        blackTimeRunning = true;
    }

    private void stopBlackTimer() {
        countDownTimer.cancel();
        whiteTimeRunning = true;
        blackTimeRunning = false;
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

    private void startBlackTimer() {

        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateBlackTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        blackTimeRunning = true;
    }

    private void updateWhiteTimer() {
        String timeLeftForWhite = getTimeLeft();

        textTimeWhite.setText(timeLeftForWhite);
    }

    private void updateBlackTimer()
    {
        String timeLeftForBlack = getTimeLeft();

        textTimeBlack.setText(timeLeftForBlack);
    }

    @NonNull
    private String getTimeLeft() {
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
        return timeLeft;
    }
}