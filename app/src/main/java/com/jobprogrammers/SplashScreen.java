package com.jobprogrammers;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.jobprogrammers.indashadows.R;

/**
 * Created by jt612 on 10/15/14.
 */
public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT;
    MediaPlayer mp;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SPLASH_TIME_OUT = 4000;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        mp = MediaPlayer.create(this, R.raw.gong);
        mp.setVolume(.5f, .5f);
        mp.setLooping(false);
        mp.setScreenOnWhilePlaying(true);
        mp.start();


        handler = new Handler();
        handler.postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, AndEngineSplashScreen.class);
                startActivity(i);

                // close this activity
                finish();
                if (isFinishing()) {
                    mp.stop();
                    mp.release();
                }
            }
        }, SPLASH_TIME_OUT);


    }


}