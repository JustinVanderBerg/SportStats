package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RecordBasketballGame extends AppCompatActivity implements View.OnClickListener {
    //new basketball game
    private AbstractGame game;
    //5 players on court at all times, therefore number of players on bench is total players subtract 5
    private int numBenchPlayers;
    private final int numCourtPlayers = 5;
    //buttons for the bench players
    private ToggleButton[] benchPlayers;
    private ToggleButton[] courtPlayers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_basketball_game);
        game = (AbstractGame) (getIntent().getSerializableExtra("Game Class"));
        numBenchPlayers = game.getNumPlayers() - 5;
        benchPlayers = new ToggleButton[numBenchPlayers];
        courtPlayers = new ToggleButton[numCourtPlayers];
        setTitle("Record Game");
        //change bench label text transparency
        generateBenchPlayers();
        generateCourtPlayers();
    }

    /**
     * This method is called whenever the user clicks on the screen
     *
     * @param view Current view that the user is on
     */
    public void onClick(View view) {
        checkToggleButtons(view);
    }

    private void checkToggleButtons(View view) {
        checkBenchButtons(view);
        checkCourtButtons(view);
    }

    private void checkBenchButtons(View view) {
        //only allow one bench player to be selected at once
        int btnClicked = -1;
        //check which button was clicked
        for (int i = 0; i < numBenchPlayers; i++) {
            if (view.getId() == this.getResources().getIdentifier(("bench" + (i + 1)), "id", this.getPackageName())) {
                btnClicked = i;
                benchPlayers[i].setChecked(true);
                i = numBenchPlayers;
            }
        }
        //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
        if (btnClicked != -1) {
            //turn off all buttons but the button that was clicked
            for (int i = 0; i < btnClicked; i++) {
                benchPlayers[i].setChecked(false);
            }
            for (int i = btnClicked + 1; i < numBenchPlayers; i++) {
                benchPlayers[i].setChecked(false);
            }
        }
    }

    private void checkCourtButtons(View view) {

    }

    private void generateBenchPlayers() {
        TextView benchLabel = findViewById(R.id.benchPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());
        //only add players too bench when there are players to add
        if (numBenchPlayers > 0) {
            //layout where all the benchPlayers get placed
            LinearLayout layout = findViewById(R.id.benchPlayers);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
            //generate two benchPlayers
            for (int i = 0; i < numBenchPlayers; i++) {
                //make a new toggle button(either on or off)
                ToggleButton toggleButton = new ToggleButton(this);
                //set the size of the toggle button
                toggleButton.setLayoutParams(params);
                //get and set id(possibility of 15 bench players max)
                int id = this.getResources().getIdentifier(("bench" + (i + 1)), "id", this.getPackageName());
                toggleButton.setId(id);
                //set text padding and text of the button
                toggleButton.setPadding(3, 3, 3, 3);
                //first five players are initially on court, so start at 6th player
                toggleButton.setTextOn(game.getAbstractHuman(i + 5).getName());
                toggleButton.setTextOff(game.getAbstractHuman(i + 5).getName());
                toggleButton.setText(game.getAbstractHuman(i + 5).getName());
                //add it to the linear layout view
                layout.addView(toggleButton);
                //add the on click listener
                toggleButton.setOnClickListener(this);
                //default to not being selected
                toggleButton.setChecked(false);

                //add to array for easy manipulation
                benchPlayers[i] = toggleButton;
            }
            //default to first bench player being selected
            benchPlayers[0].setChecked(true);
        }
    }

    private void generateCourtPlayers() {
        TextView benchLabel = findViewById(R.id.courtPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());
        //layout where all the court players get placed
        LinearLayout layout = findViewById(R.id.courtPlayers);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        //generate two benchPlayers
        for (int i = 0; i < numCourtPlayers; i++) {
            //make a new toggle button(either on or off)
            ToggleButton toggleButton = new ToggleButton(this);
            //set the size of the toggle button
            toggleButton.setLayoutParams(params);
            //get and set id(possibility of 15 bench players max)
            int id = this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName());
            toggleButton.setId(id);
            //set text padding and text of the button
            toggleButton.setPadding(3, 3, 3, 3);
            toggleButton.setTextOn(game.getAbstractHuman(i).getName());
            toggleButton.setTextOff(game.getAbstractHuman(i).getName());
            toggleButton.setText(game.getAbstractHuman(i).getName());
            //add it to the linear layout view
            layout.addView(toggleButton);
            //add the on click listener
            toggleButton.setOnClickListener(this);
            //default to not being selected
            toggleButton.setChecked(false);

            //add to array for easy manipulation
            courtPlayers[i] = toggleButton;
        }
        //default to first bench player being selected
        courtPlayers[0].setChecked(true);

    }
}
