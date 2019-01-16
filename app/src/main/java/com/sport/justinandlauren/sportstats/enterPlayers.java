package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

public class enterPlayers extends AppCompatActivity {
    private AbstractGame mainGame;
    private EditText[] names;
    private EditText[] numbers;
    private AbstractHuman[] players;
    //context used in alert builder dialog
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_players);
        //get the game class, in order to access number of players, and modify the player array
        mainGame = (AbstractGame) getIntent().getSerializableExtra("Game Class");
        names = new EditText[mainGame.getNumPlayers()];
        numbers = new EditText[mainGame.getNumPlayers()];
        players = new BasketballPlayer[mainGame.getNumPlayers()];
        resetPlayerArray();

        //get the scroll area to put the player input boxes in
        LinearLayout playerNames = findViewById(R.id.inputNames);
        LinearLayout playerNumbers = findViewById(R.id.inputNumbers);

        //create an edit text box for each of the players
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            //create a new edit text box
            EditText tempEditText = new EditText(this);
            tempEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            //set the prompt for the text field
            tempEditText.setHint("INITIAL. LastName");
            tempEditText.setText("Player name");
            playerNames.addView(tempEditText);
            names[i] = tempEditText;
        }

        //create an edit text box for each of the players
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            //create a new edit text box
            EditText tempEditText = new EditText(this);
            tempEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            //set the prompt for the text field
            tempEditText.setHint("Player number");
            tempEditText.setText((mainGame.getNumPlayers() - i) + "");
            playerNumbers.addView(tempEditText);
            numbers[i] = tempEditText;
        }
    }



    /**
     * Called when user selects the record game button
     *
     * @param view view the player clicked on
     */
    public void displayRecordGame(View view) {
        //variable to make sure to only show record game screen if all data is entered correctly
        boolean error = false;

        int minutesPerQuarter;
        //get all names and numbers
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            try {
                players[i].setName(names[i].getText().toString());
                players[i].setPlayerNumber(Integer.parseInt(numbers[i].getText().toString()));

            } catch (NumberFormatException e) {
                //error on users input
                error = true;
            }
        }
        try {
            EditText temp = findViewById(R.id.numMinutesPerQuarter);
            minutesPerQuarter = Integer.parseInt(temp.getText().toString());
            long secondsPerQuarter = minutesPerQuarter * 60000;
            mainGame.setGameLength(secondsPerQuarter);
            Switch keepSort = findViewById(R.id.benchSort);
            mainGame.setKeepSorted(keepSort.isChecked());
        } catch (NumberFormatException e) {
            error = true;
        }
        //only launch the game recording screen if all data is entered correctly
        if (!error) {
            mainGame.setPlayers(players);
            //sort the players array from highest to lowest based off player number
            mainGame.sortPlayers(0, players.length - 1);
            //update the players array in the main game

            Intent intent = new Intent(this, RecordBasketballGame.class);
            intent.putExtra("Game Class", mainGame);
            startActivity(intent);
        } else {
            //make a new alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //set the title
            builder.setTitle("Error");
            //set the message and set the type of buttons
            builder.setMessage("Check that all data is entered, and is correct")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //don't start the record game if incorrect
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            resetPlayerArray();
        }

    }

    /**
     * Used to reset the player array, so that any values from before the error was thrown are no longer stored
     */
    private void resetPlayerArray() {
        //initialize player array
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            players[i] = new BasketballPlayer();
        }
    }
}
