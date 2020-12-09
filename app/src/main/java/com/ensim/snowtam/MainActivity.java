package com.ensim.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.ensim.snowtam.Controller.Controller;
import com.ensim.snowtam.View.HomePageActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2500; //2,5 s de splash screen

    Animation top_animation;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        //Hooks
        image = findViewById(R.id.logo);

        image.setAnimation(top_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }


}