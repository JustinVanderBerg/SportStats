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
            tempEditText.setHint((i + 1) + ". Player name");
            tempEditText.setText((i + 1) + ". Player name");
            playerNames.addView(tempEditText);
            names[i] = tempEditText;
        }

        //create an edit text box for each of the players
        for (int i = 0; i < mainGame.getNumPlayers(); i++) {
            //create a new edit text box
            EditText tempEditText = new EditText(this);
            tempEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            //set the prompt for the text field
            tempEditText.setHint((i + 1) + ". Player number");
            tempEditText.setText((i + 1) + "");
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
            //sort the players array from highest to lowest based off player number
            sort(players, 0, players.length - 1);
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
                            //don't start the record game if incorrect
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

    /**
     * merge sort ascending
     *
     * @param arr  array to sort
     * @param low  low value of the segment to sort
     * @param high high value of the segment to sort
     */
    private void sort(AbstractHuman arr[], int low, int high) {
        //base case
        if (low < high) {

            int middle = (low + high) / 2;
            sort(arr, low, middle);
            sort(arr, middle + 1, high);
            merge(arr, low, middle, high);
        }
    }

    /**
     * merge sort ascending combine segments
     *
     * @param arr    array to sort
     * @param low    low value of the segment to sort
     * @param middle middle value to split the array around
     * @param high   high value of the segment to sort
     */
    private void merge(AbstractHuman[] arr, int low, int middle, int high) {

        int numLeft = (middle - low + 1);
        int numRight = (high - middle);
        //left segment of array
        AbstractHuman[] L = new BasketballPlayer[numLeft];
        //right segment of array
        AbstractHuman[] R = new BasketballPlayer[numRight];
        //fill the left array with the left section of the array
        for (int i = 0; i < numLeft; i++) {
            L[i] = arr[i + low];
        }
        //fill the right array with the left segment of the array
        for (int i = 0; i < numRight; i++) {
            R[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0, k = low;
        //while there are still elements left in the left and right array
        while (i < numLeft && j < numRight) {
            //if the element in the left array is less than right array set array at current location to left element
            if (L[i].getPlayerNumber() <= R[j].getPlayerNumber()) {
                arr[k] = L[i];
                i++;

            } else {//else the right element is less, therefore set the array a current location to right element

                arr[k] = R[j];
                j++;
            }
            k++;
        }
        //merge the rest of the left segment back into the array, if elements remain
        while (i < numLeft) {
            arr[k] = L[i];
            i++;
            k++;
        }
        //merge the rest of the right segment back into the array, if elements remain
        while (j < numRight) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
