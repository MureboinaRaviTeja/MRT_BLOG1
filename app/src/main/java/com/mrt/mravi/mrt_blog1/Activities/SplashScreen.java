package com.mrt.mravi.mrt_blog1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mrt.mravi.mrt_blog1.R;

/**
 * Created by mravi on 16-02-2018.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        int SPLASH_TIMER = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this,MainActivityMD7.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);

        //Intent intent =new Intent(this,MainActivity.class);
        //startActivity(intent);

        //finish();

    }
}
