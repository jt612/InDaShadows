package com.jobprogrammers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.jobprogrammers.indashadows.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @seeSystemUiHider
 */
public class IDShadows extends Activity {
    //MediaPlayer mp;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.idshadows);
        /*mp = MediaPlayer.create(IDShadows.this, R.raw.ninja);
        mp.setLooping(true);
        mp.setVolume(0, 0);
        mp.start();*/
        final View controlsView = findViewById(R.id.fullscreen_content);
        final View contentView = findViewById(R.id.fullscreen_content);

    }

    @Override
    protected void onPause() {
        onDestroy();

    }

    protected void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void start(View button) {
        Intent i = new Intent(IDShadows.this, SplashScreen2startGame.class);
        startActivity(i);
        this.finish();
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.startButton:
                this.start(button);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
