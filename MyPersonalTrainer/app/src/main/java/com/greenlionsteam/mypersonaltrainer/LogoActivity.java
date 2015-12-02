package com.greenlionsteam.mypersonaltrainer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.greenlionsteam.mypersonaltrainer.Models.JsonFetcher;
import com.greenlionsteam.mypersonaltrainer.Models.TrainingModel;


public class LogoActivity extends AppCompatActivity {

    private ImageView logoView;
    private Animation logoAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_logo);
        initViews();
        startLogoAnimation();

        //new DownloadFilesTask().execute();

    }



    private void initViews() {
        logoView = (ImageView) findViewById(R.id.logo_view);
    }

    private void startLogoAnimation() {
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainMenuActivity();
            }

            @Override public void onAnimationRepeat(Animation animation) {}
        });
        logoView.startAnimation(logoAnimation);
    }

    private void startMainMenuActivity() {
        Intent intent = new Intent(this, MainFeedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
