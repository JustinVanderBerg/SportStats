package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.FileInputStream;
import java.io.IOException;

public class SelectGame extends AppCompatActivity implements View.OnClickListener {
    private AbstractSport season = null;
    private Button games[];
    private int numGames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        setTitle("Select Game");
        season = getPastGames();
        numGames = season.getGames().size();
        games = new Button[numGames];
        generateGameButtons();
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
     * Generate the buttons for the user to click on to view the games stats for
     */
    public void generateGameButtons() {
        AbstractGame gamesPlayed[] = new AbstractGame[numGames];
        gamesPlayed = season.getGames().toArray(gamesPlayed);
        //only show games if there are games to show
        if (numGames > 0) {

            //layout where all the playerButtons get placed
            LinearLayout layout = findViewById(R.id.gameSelectButtons);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //generate two playerButtons
            for (int i = 0; i < numGames; i++) {

                //make a new button
                Button button = new Button(this);
                //set the size of the button
                button.setLayoutParams(params);

                //get and set id(possibility of 20 players max)
                int id = this.getResources().getIdentifier(("player" + (i + 1)), "id", this.getPackageName());
                button.setId(id);
                //set text padding and text of the button
                button.setPadding(20, 3, 20, 3);
                button.setGravity(Gravity.CENTER);
                button.setMinWidth(100);
                button.setMinHeight(100);
                button.setLineSpacing(0, 0.8f);
                //set the text of the button
                button.setText(gamesPlayed[i].getDate());

                //add it to the linear layout view
                layout.addView(button);
                //add the on click listener
                button.setOnClickListener(this);

                //add to array for easy manipulation
                games[i] = button;
            }
        }
    }


    /**
     * This method is called whenever the user clicks on a button that uses this click listener(court buttons, player modification buttons)
     *
     * @param view View that the user clicked
     */
    public void onClick(View view) {

    }

}
