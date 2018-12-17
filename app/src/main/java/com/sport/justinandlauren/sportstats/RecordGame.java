package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecordGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_game);
        setTitle("Record Game");
    }

    public void MainMenu(View view){
        Intent intent = new Intent(this,LoadScreen.class);
        startActivity(intent);
    }
}
