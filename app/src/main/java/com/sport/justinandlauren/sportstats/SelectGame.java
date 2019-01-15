package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.IOException;

public class SelectGame extends AppCompatActivity {
    private AbstractSport season = null;
    private Button games[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        setTitle("Select Game");
        season = getPastGames();
        //generateGameButtons();
    }

    private AbstractSport getPastGames() {
        //get a new input stream from the past games in order to read the file
        FileInputStream fis = null;
        try {
            //try and open file
            fis = openFileInput(getString(R.string.gameHistoryFilename));
        } catch (IOException e) {
            Log.e("readFile", "Error reading past games: " + e);
        }
        AbstractSport season = AbstractSport.getSeasonFromFile(fis);
        return season;

    }
    /**
     public void generateGameButtons() {
     numPlayers = game.getNumPlayers();
     playerButtons = new ToggleButton[numPlayers];
     //only add players too bench when there are players to add
     if (numPlayers > 0) {

     //layout where all the playerButtons get placed
     LinearLayout layout = findViewById(R.id.btnLayout);
     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

     //generate two playerButtons
     for (int i = 0; i < numPlayers; i++) {

     //make a new toggle button(either on or off)
     ToggleButton toggleButton = new ToggleButton(this);
     //set the size of the toggle button
     toggleButton.setLayoutParams(params);

     //get and set id(possibility of 15 bench players max)
     int id = this.getResources().getIdentifier(("player" + (i + 1)), "id", this.getPackageName());
     toggleButton.setId(id);
     //set text padding and text of the button
     toggleButton.setPadding(3, 3, 3, 3);
     toggleButton.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
     toggleButton.setMinWidth(110);
     toggleButton.setMinHeight(105);
     toggleButton.setLineSpacing(0, 0.8f);
     //first five players are initially on court, so start at 6th player
     //set the text of the button
     //setText(toggleButton, "\n" + game.getHuman(i).getPlayerNumber() + "\n\n" + game.getHuman(i).getName());

     //add it to the linear layout view
     layout.addView(toggleButton);
     //add the on click listener
     toggleButton.setOnClickListener(this);
     //default to not being selected
     toggleButton.setChecked(false);

     //add to array for easy manipulation
     //playerButtons[i] = toggleButton;
     }
     }
     }
     **/
}
