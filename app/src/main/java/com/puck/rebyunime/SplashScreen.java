package com.puck.rebyunime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


/*  Nama : Adhitya Rizqy Pratama
    Nim : 10117134
    Kelas : AKB-4/IF-4

    Tanggal : 21 Juni 2020
    Waktu : 14.45
    Selesai Mengerjakan Splash Screen
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1500;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo,info;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        info = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        info.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("FirstTime",true);

                 if(isFirstTime){

                     SharedPreferences.Editor editor = onBoardingScreen.edit();
                     editor.putBoolean("FirstTime",false);
                     editor.commit();

                     Intent intent = new Intent(getApplicationContext(),OnBoarding.class);
                     startActivity(intent);
                     finish();

                 }
                 else{
                     Intent intent = new Intent(getApplicationContext(),Home.class);
                     startActivity(intent);
                     finish();

                 }

            }
        },SPLASH_SCREEN);
    }
}
