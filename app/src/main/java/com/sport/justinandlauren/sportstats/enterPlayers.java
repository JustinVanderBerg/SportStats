package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class enterPlayers extends AppCompatActivity {
    private AbstractGame mainGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainGame = (AbstractGame) getIntent().getSerializableExtra("Game Class");
        setContentView(R.layout.activity_enter_players);
    }
    //Called when user selects the record game button
    public void displayRecordGame(View view){
        Intent intent = new Intent(this, RecordBasketballGame.class);
        intent.putExtra("Game Class", mainGame);
        startActivity(intent);
    }
}
