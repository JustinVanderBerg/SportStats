/*
 * Lauren Thomas and Justin VanderBerg
 * January 17, 2019
 * This class shows the splash/load screen of the app for 3 seconds, before showing the main menu and
 * disposing of the splash screen so the user cannot access it, even with the android back button
 */
package com.sport.justinandlauren.sportstats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Context context = this;
        //show app splash screen for 2 seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context,LoadScreen.class);
                startActivity(intent);
                finish();
            }

        },3000);//number of milliseconds to show splash screen for
    }

}
