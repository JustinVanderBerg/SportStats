package com.sport.justinandlauren.sportstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);
        setTitle("Player Stats");
    }
}
