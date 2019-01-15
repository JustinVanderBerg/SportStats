package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);
        setTitle("Game Stats");

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, SelectGame.class);
        startActivity(intent);
    }
}
