package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ViewGame extends AppCompatActivity implements View.OnClickListener {
    private ToggleButton playerButtons[];
    private int playerSelectedLocation;
    private int numPlayers;
    BasketballGame game = null;
    private boolean comeFromRecordGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);
        //get the game passed from the previous page, either the record game or the select game page
        AbstractGame tempGame = (AbstractGame) getIntent().getSerializableExtra("gameClass");
        //whether the user came from record game or select game.
        comeFromRecordGame = getIntent().getBooleanExtra("recordGame", false);
        game = (BasketballGame) tempGame;
        //resort the players array
        game.sortPlayers(0, game.getNumPlayers() - 1);
        setTitle("Game Stats for " + game.getDate());
        showGameStats();
        generatePlayerButtons();
    }

    /**
     * Method to show the team stats for the game
     */
    public void showGameStats() {
        //Show the game stats
        ((TextView) findViewById(R.id.txtGameOutput1)).setText(game.toString1());
        ((TextView) findViewById(R.id.txtGameOutput2)).setText(game.toString2());
        ((TextView) findViewById(R.id.txtGameOutput3)).setText(game.toString3());
    }

    /**
     * Method to show player stats for the game
     */
    public void showPlayerStats() {
        //show the player stats
        ((TextView) findViewById(R.id.txtPlayerOutput1)).setText(game.getHuman(playerSelectedLocation).toString1());
        ((TextView) findViewById(R.id.txtPlayerOutput2)).setText(game.getHuman(playerSelectedLocation).toString2());
        ((TextView) findViewById(R.id.txtPlayerOutput3)).setText(game.getHuman(playerSelectedLocation).toString3());
    }

    /**
     * Method to generate the buttons for the players who played in the game
     */
    public void generatePlayerButtons() {
        numPlayers = game.getNumPlayers();
        playerButtons = new ToggleButton[numPlayers];
        //only add players too bench when there are players to add
        if (numPlayers > 0) {

            //layout where all the playerButtons get placed
            LinearLayout layout = findViewById(R.id.btnLayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //generate the player buttons
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
                setText(toggleButton, "\n" + game.getHuman(i).getPlayerNumber() + "\n\n" + game.getHuman(i).getName());

                //add it to the linear layout view
                layout.addView(toggleButton);
                //add the on click listener
                toggleButton.setOnClickListener(this);
                //default to not being selected
                toggleButton.setChecked(false);

                //add to array for easy manipulation
                playerButtons[i] = toggleButton;
            }
        }
    }


    /**
     * Method to set the text of a toggle button (on text, off text, and main text)
     *
     * @param tempButton toggle button to set the text of
     * @param text       String to set the text to
     */
    private void setText(ToggleButton tempButton, String text) {
        tempButton.setTextOn(text);
        tempButton.setTextOff(text);
        tempButton.setText(text);
    }

    /**
     * This method is called whenever the user clicks on a button that uses this click listener
     *
     * @param view View that the user clicked
     */
    public void onClick(View view) {
        checkPlayerButtons(view);
    }

    /**
     * Check if the user clicked on one of the court buttons
     *
     * @param view The view that the user clicked
     */
    private void checkPlayerButtons(View view) {

        //only allow one bench player to be selected at once
        int btnClicked = -1;
        //check which button was clicked
        for (int i = 0; i < numPlayers; i++) {
            if (view.getId() == this.getResources().getIdentifier(("player" + (i + 1)), "id", this.getPackageName())) {
                //store the selected location
                btnClicked = i;
                playerSelectedLocation = i;
                //check the button the user clicked
                checkButton(playerButtons[i]);
                i = numPlayers;
            }
        }
        //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
        if (btnClicked != -1) {
            //turn off all buttons but the button that was clicked
            for (int i = 0; i < btnClicked; i++) {
                //uncheck button
                uncheckButton(playerButtons[i]);
            }
            for (int i = btnClicked + 1; i < numPlayers; i++) {
                //uncheck button
                uncheckButton(playerButtons[i]);
            }
            //show player info of clicked button
            showPlayerStats();
        }

    }

    /**
     * Method to set the text to black, and deselect a toggle button--used for court and bench buttons
     *
     * @param tempButton toggle button to deselect
     */
    private void uncheckButton(ToggleButton tempButton) {
        tempButton.setChecked(false);
        //change the text color back
        tempButton.setTextColor(Color.BLACK);
        setText(tempButton, tempButton.getText().toString());
    }

    /**
     * Method to set text to red and then select a toggle button
     *
     * @param tempButton toggle button to deselect
     */
    private void checkButton(ToggleButton tempButton) {
        tempButton.setChecked(true);
        //set text color to green
        tempButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
        setText(tempButton, tempButton.getText().toString());
    }

    /**
     * Method to allow the user to select a different game
     *
     * @param view view the player clicked on
     */
    public void switchGameClick(View view) {
        if (comeFromRecordGame) {
            AbstractSport seasonStats = null;
            FileOutputStream fos = null;
            boolean error = false;
            try {
                //get file that stores all the season stats
                FileInputStream fis = openFileInput(getString(R.string.gameHistoryFilename));
                //get the season stats object from the file
                seasonStats = AbstractSport.getSeasonFromFile(fis);
                //attempt to open a file output stream to store the updated season stats
                fos = openFileOutput(getString(R.string.gameHistoryFilename), MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                Log.e("readFileError", "Error reading season stats from file: " + e);
                error = true;
            }
            //if there was no errors in finding the files, finish the game, update data and store on file
            if (!error) {
                //add the game to the season
                seasonStats.addGame(game);
                //check who won the game, and update the number of games won if the user's team won
                if (game.getPointsFor() > game.getPointsAgainst()) {
                    seasonStats.setGamesWon(seasonStats.getGamesWon() + 1);
                }
                //add one to the number of games played in the season
                seasonStats.setGamesPlayed(seasonStats.getGamesPlayed() + 1);
                //try to write the updated season info to the file
                seasonStats.writeToFile(fos);

            }
        }
        //show the load screen
        Intent intent = new Intent(this, LoadScreen.class);
        startActivity(intent);
    }
}
