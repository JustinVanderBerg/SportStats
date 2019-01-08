package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class enterPlayers extends AppCompatActivity {
    private AbstractGame mainGame;
    private EditText[] names;
    private EditText[] numbers;
    private AbstractHuman[] players;
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
            tempEditText.setHint(i + ". Player name");
            playerNames.addView(tempEditText);
            names[i] = tempEditText;
        }

        //create an edit text box for each of the players
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            //create a new edit text box
            EditText tempEditText = new EditText(this);
            tempEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            //set the prompt for the text field
            tempEditText.setHint(i + ". Player number");
            playerNumbers.addView(tempEditText);
            numbers[i] = tempEditText;
        }
    }
    //Called when user selects the record game button
    public void displayRecordGame(View view){
        //variable to make sure to only show record game screen if all data is entered correctly
        boolean error = false;
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

        //only launch the game recording screen if all data is entered correctly
        if (!error) {
            //update the players array in the main game
            mainGame.setPlayers(players);
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
                            // TODO: handle the OK
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            resetPlayerArray();
        }

    }

    private void resetPlayerArray() {
        //initialize player array
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            players[i] = new BasketballPlayer();
        }
    }
}
