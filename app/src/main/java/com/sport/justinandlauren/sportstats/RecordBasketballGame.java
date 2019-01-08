package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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
    private ToggleButton[] benchPlayerButtons;
    private ToggleButton[] courtPlayerButtons;

    //variable to allow substitutions
    private boolean benchSelected = false;
    private int benchSelectedLocation = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_basketball_game);
        game = (AbstractGame) (getIntent().getSerializableExtra("Game Class"));
        numBenchPlayers = game.getNumPlayers() - 5;
        benchPlayerButtons = new ToggleButton[numBenchPlayers];
        courtPlayerButtons = new ToggleButton[numCourtPlayers];
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
                //allow user to deselect bench player if they click the bench player again
                if (!benchPlayerButtons[i].isChecked()) {
                    benchPlayerButtons[i].setChecked(false);
                    benchSelected = false;
                    benchSelectedLocation = -1;
                } else {
                    benchPlayerButtons[i].setChecked(true);
                    benchSelected = true;
                    benchSelectedLocation = i;
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //set title
                    builder.setTitle("Substitution");
                    builder.setMessage("Please click the player on the court you would like to substitute with, or on the bench player again to cancel")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                //stop checking if other buttons were clicked
                i = numBenchPlayers;
            }
        }
        //handle substitution--bench players are only clicked to substitute them


        //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
        if (btnClicked != -1) {
            //turn off all buttons but the button that was clicked
            for (int i = 0; i < btnClicked; i++) {
                benchPlayerButtons[i].setChecked(false);
            }
            for (int i = btnClicked + 1; i < numBenchPlayers; i++) {
                benchPlayerButtons[i].setChecked(false);
            }

        }
    }

    private void checkCourtButtons(View view) {
        //do substitution if one of the bench players is selected
        if (benchSelected) {
            //make sure that the bench location is valid
            if (benchSelectedLocation != -1) {

            }
        } else {
            //only allow one bench player to be selected at once
            int btnClicked = -1;
            //check which button was clicked
            for (int i = 0; i < numCourtPlayers; i++) {
                if (view.getId() == this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName())) {
                    btnClicked = i;
                    courtPlayerButtons[i].setChecked(true);
                    i = numCourtPlayers;
                }
            }
            //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
            if (btnClicked != -1) {
                //turn off all buttons but the button that was clicked
                for (int i = 0; i < btnClicked; i++) {
                    courtPlayerButtons[i].setChecked(false);
                }
                for (int i = btnClicked + 1; i < numCourtPlayers; i++) {
                    courtPlayerButtons[i].setChecked(false);
                }
            }
        }
    }

    private void generateBenchPlayers() {
        TextView benchLabel = findViewById(R.id.benchPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());
        //only add players too bench when there are players to add
        if (numBenchPlayers > 0) {
            //layout where all the benchPlayerButtons get placed
            LinearLayout layout = findViewById(R.id.benchPlayers);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
            //generate two benchPlayerButtons
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
                toggleButton.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                //first five players are initially on court, so start at 6th player
                toggleButton.setTextOn("\n" + game.getAbstractHuman(i + 5).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i + 5).getName());
                toggleButton.setTextOff("\n" + game.getAbstractHuman(i + 5).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i + 5).getName());
                toggleButton.setText("\n" + game.getAbstractHuman(i + 5).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i + 5).getName());
                //add it to the linear layout view
                layout.addView(toggleButton);
                //add the on click listener
                toggleButton.setOnClickListener(this);
                //default to not being selected
                toggleButton.setChecked(false);

                //add to array for easy manipulation
                benchPlayerButtons[i] = toggleButton;
            }
        }
    }

    private void generateCourtPlayers() {
        TextView benchLabel = findViewById(R.id.courtPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());
        //layout where all the court players get placed
        LinearLayout layout = findViewById(R.id.courtPlayers);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        //generate two benchPlayerButtons
        for (int i = 0; i < numCourtPlayers; i++) {
            //make a new toggle button(either on or off)
            ToggleButton toggleButton = new ToggleButton(this);
            //set the size of the toggle button
            toggleButton.setLayoutParams(params);
            //get and set id(possibility of 15 bench players max)
            int id = this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName());
            toggleButton.setId(id);
            //text location on the button
            toggleButton.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            //set text padding and text of the button
            toggleButton.setPadding(3, 3, 3, 3);
            toggleButton.setTextOn("\n" + game.getAbstractHuman(i).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i).getName());
            toggleButton.setTextOff("\n" + game.getAbstractHuman(i).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i).getName());
            toggleButton.setText("\n" + game.getAbstractHuman(i).getPlayerNumber() + "\n\n" + game.getAbstractHuman(i).getName());
            //add it to the linear layout view
            layout.addView(toggleButton);
            //add the on click listener
            toggleButton.setOnClickListener(this);
            //default to not being selected
            toggleButton.setChecked(false);

            //add to array for easy manipulation
            courtPlayerButtons[i] = toggleButton;
        }
        //default to first bench player being selected
        courtPlayerButtons[0].setChecked(true);
    }

    /**
     * Method used to substitute players
     *
     * @param bench Player currently on the bench, who will be swapped with the player on the court
     * @param court Player on the court who will be swapped with the player on the bench
     */
    private void substitution(AbstractHuman bench, AbstractHuman court) {
        AbstractHuman temp;
        temp = bench;

    }
}
