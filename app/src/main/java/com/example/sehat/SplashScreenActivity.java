package com.example.sehat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Optional: Set the view to full screen (removes the status bar)
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                     WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Connect this Activity with your XML layout (activity_splash.xml)
        setContentView(R.layout.activity_splash);

        // Use Handler to delay the Activity transition
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // UPDATE: Navigate to OnboardingActivity (Scene 1.5)
                Intent i = new Intent(SplashScreenActivity.this, OnboardingActivity.class);

                startActivity(i); // Start the next Activity

                // Close the current Activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}