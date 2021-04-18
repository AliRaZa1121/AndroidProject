package com.farhan.sps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.farhan.sps.R;
import com.farhan.sps.databinding.ActivitySplashBinding;
import com.farhan.sps.utils.SharedPrefrencesManager;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        animation.reset();
        binding.logo.startAnimation(animation);

        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in);
        titleAnim.reset();
        binding.appTitle.startAnimation(titleAnim);


        int SPLASH_DISPLAY_LENGTH = 4000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (SharedPrefrencesManager.getInstance(SplashActivity.this).isLoggedIn()) {
                    Intent mainIntent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}