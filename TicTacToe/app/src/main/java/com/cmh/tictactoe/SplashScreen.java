package com.cmh.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    ImageView backgroundImage;
    Animation slideAnimation;
    Integer SPLASH_SCREEN_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE
        backgroundImage = (ImageView) findViewById(R.id.SplashScreenImage);
        slideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        backgroundImage.startAnimation(slideAnimation);

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN_TIME_OUT); // 4000 is the delayed time in milliseconds.
    }
}