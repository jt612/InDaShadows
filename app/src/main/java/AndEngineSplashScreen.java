import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.jobprogrammers.indashadows.IDShadows;
import com.jobprogrammers.indashadows.R;


/**
 * Created by jt612 on 12/22/14.
 */
public class AndEngineSplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SPLASH_TIME_OUT = 4000;
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.andenginesplashscreen);

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
                Intent i = new Intent(AndEngineSplashScreen.this, IDShadows.class);

                // close this activity
                startActivity(i);
                finish();


            }
        }, SPLASH_TIME_OUT);
    }


}



