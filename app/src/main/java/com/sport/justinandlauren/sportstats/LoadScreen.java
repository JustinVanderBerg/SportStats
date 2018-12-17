package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoadScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
    }

    //Called when user selects the record game button
    public void displayRecordGame(View view){
        Intent intent = new Intent(this, RecordGame.class);
        startActivity(intent);
    }
    //display player stats
    public void displayPlayerStats(View view){
        Intent intent = new Intent(this,ViewPlayer.class);
        startActivity(intent);
    }
    //display team stats
    public void displayGameStats(View view){
        Intent intent = new Intent(this,ViewGame.class);
        startActivity(intent);
    }
}
