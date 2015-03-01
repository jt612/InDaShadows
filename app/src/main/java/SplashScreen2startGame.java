/**
 * Created by jt612 on 10/15/14.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.jobprogrammers.indashadows.R;
import com.jobprogrammers.indashadows.startGame;

public class SplashScreen2startGame extends Activity {
    ProgressBar pg;
    int progress = 0;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash2start);

        pg = (ProgressBar) findViewById(R.id.splashscreenprogressbar);
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress += 10;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            pg.setProgress(progress);
                            if (progress == pg.getMax()) {
                                pg.setVisibility(View.INVISIBLE);
                                Intent i = new Intent(getApplicationContext(), startGame.class);
                                startActivity(i);

                            }

                        }
                    });

                }
            }
        }).start();

    }
}