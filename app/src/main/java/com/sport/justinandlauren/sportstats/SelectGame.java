package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

public class SelectGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        setTitle("Select Game");
        generateGameButtons();
    }

    private void generateGameButtons() {
        //get a new input stream from the past games in order to read the file
        FileInputStream fis = null;
        try {
            //try and open file
            fis = openFileInput(getString(R.string.gameHistoryFilename));
        } catch (IOException e) {
            Log.e("readFile", "Error reading past games: " + e);
        }
        AbstractSport season = AbstractSport.getSeasonFromFile(fis);

    }
}
