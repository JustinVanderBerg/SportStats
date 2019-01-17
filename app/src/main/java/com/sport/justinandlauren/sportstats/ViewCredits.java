/*
 * Lauren Thomas and Justin VanderBerg
 * January 17, 2019
 * This is a class that shows the user the credits for the app, and allows the user to return to the
 * main menu through the menu bar back button
 */
package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewCredits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_credits);
        setTitle("Credits");
    }
}
