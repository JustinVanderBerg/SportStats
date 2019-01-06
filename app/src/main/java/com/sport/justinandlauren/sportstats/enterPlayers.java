package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class enterPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_players);
    }
    //Called when user selects the record game button
    public void displayRecordGame(View view){
        Intent intent = new Intent(this, RecordBasketballGame.class);
        startActivity(intent);
    }
}
