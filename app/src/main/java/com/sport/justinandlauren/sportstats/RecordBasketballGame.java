package com.sport.justinandlauren.sportstats;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

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

    //Variable to hold whether the game is going or not
    private boolean gameGoing = false;
    private long timeLeftInPeriod = -1;
    private DecimalFormat timerFormat = new DecimalFormat("00");
    CountDownTimer gameTime;

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
        Button btnTimer = findViewById(R.id.btnTimer);
        //set the initial start time of the period
        long numMinute, numSeconds;
        timeLeftInPeriod = game.getGameLength();
        //switch the milliseconds game length to minutes and seconds
        numMinute = (timeLeftInPeriod / 1000) / 60;
        numSeconds = (timeLeftInPeriod / 1000) % 60;
        //set color to red because it isn't going
        btnTimer.setTextColor(Color.RED);
        btnTimer.setText(numMinute + ":" + timerFormat.format(numSeconds));
    }

    /**
     * Method that is called when the timer button is clicked
     *
     * @param view Current view
     */
    public void timerClick(View view) {
        final Button btnTimer = (Button) view;

        //pause the game if they click the timer and the time is currently going
        if (gameGoing) {
            //pause the game, call the finish function of the timer and then cancel the timer, while storing the time left on cancel
            gameGoing = false;
            gameTime.onFinish();
            gameTime.cancel();
        } else {
            gameGoing = true;
            //create a new timer, as whenever the time isn't going, the countdown timer is cancelled
            gameTime = new CountDownTimer(timeLeftInPeriod, 250) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //switch the milliseconds till finished to minutes and seconds till finished
                    setTimerText(btnTimer, Color.GREEN);
                    timeLeftInPeriod = millisUntilFinished;
                    //game is still going
                    gameGoing = true;
                }

                @Override
                public void onFinish() {

                    //if less than a second left
                    if (timeLeftInPeriod < 1000) {
                        timeLeftInPeriod = game.getGameLength();
                    }
                    //set the initial start time of the period
                    setTimerText(btnTimer, Color.RED);
                    gameGoing = false;
                }
            };
            gameTime.start();
        }
    }

    private void setTimerText(Button timer, int color) {
        long numMinute, numSeconds;
        //switch the milliseconds game length to minutes and seconds
        numMinute = (timeLeftInPeriod / 1000) / 60;
        numSeconds = (timeLeftInPeriod / 1000) % 60;
        timer.setTextColor(ColorStateList.valueOf(color));
        timer.setText(numMinute + ":" + timerFormat.format(numSeconds));
    }

    /**
     * This method is called whenever the user clicks on the screen
     *
     * @param view View that the user clicked
     */
    public void onClick(View view) {
        checkToggleButtons(view);
    }

    /**
     * method to check all the toggle buttons for the bench and the court
     *
     * @param view
     */
    private void checkToggleButtons(View view) {
        checkBenchButtons(view);
        checkCourtButtons(view);
    }

    /**
     * Method to handle the clicking of the bench buttons
     *
     * @param view The view the user clicked
     */
    private void checkBenchButtons(View view) {
        //only allow one bench player to be selected at once
        int btnClicked = -1;
        //check which button was clicked
        for (int i = 0; i < numBenchPlayers; i++) {
            if (view.getId() == this.getResources().getIdentifier(("bench" + (i + 1)), "id", this.getPackageName())) {
                btnClicked = i;
                //allow user to deselect bench player if they click the bench player again
                if (!benchPlayerButtons[i].isChecked()) {
                    uncheckButton(benchPlayerButtons[i]);
                    benchSelected = false;
                    benchSelectedLocation = -1;
                } else {
                    checkButton(benchPlayerButtons[i]);
                    benchSelected = true;
                    benchSelectedLocation = i;

                }
                //stop checking if other buttons were clicked
                i = numBenchPlayers;
            }
        }
        //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
        if (btnClicked != -1) {
            //turn off all buttons but the button that was clicked
            for (int i = 0; i < btnClicked; i++) {
                //uncheck button
                uncheckButton(benchPlayerButtons[i]);

            }
            for (int i = btnClicked + 1; i < numBenchPlayers; i++) {
                //uncheck button
                uncheckButton(benchPlayerButtons[i]);
            }

        }
    }

    /**
     * Check if the user clicked on one of the court buttons
     *
     * @param view The view that the user clicked
     */
    private void checkCourtButtons(View view) {

        //only allow one bench player to be selected at once
        int btnClicked = -1;
        //check which button was clicked
        for (int i = 0; i < numCourtPlayers; i++) {
            if (view.getId() == this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName())) {
                btnClicked = i;
                //do substitution if one of the bench players is selected
                if (benchSelected) {

                    //make sure that the bench location is valid
                    if (benchSelectedLocation != -1) {
                        substitution(benchSelectedLocation, i);
                    }
                } else {
                    checkButton(courtPlayerButtons[i]);
                    i = numCourtPlayers;
                }
            }
        }
        //only turn off buttons if one of them was pressed(i.e. there was a change in one of their toggle states)
        if (btnClicked != -1) {
            //turn off all buttons but the button that was clicked
            for (int i = 0; i < btnClicked; i++) {
                //uncheck button
                uncheckButton(courtPlayerButtons[i]);
            }
            // TODO finish green text selection for substitution
            for (int i = btnClicked + 1; i < numCourtPlayers; i++) {
                //uncheck button
                uncheckButton(courtPlayerButtons[i]);
            }
        }
    }

    /**
     * Method to set the text to black, and deselect a toggle button--used for court and bench buttons
     *
     * @param tempButton toggle button to deselect
     */
    private void uncheckButton(ToggleButton tempButton) {
        //change the text color back

        tempButton.setTextColor(Color.BLACK);
        setText(tempButton, tempButton.getText().toString());

        tempButton.setChecked(false);
    }

    /**
     * Method to set text to red and then select a toggle button
     *
     * @param tempButton toggle button to deselect
     */
    private void checkButton(ToggleButton tempButton) {
        tempButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
        setText(tempButton, tempButton.getText().toString());
        tempButton.setChecked(true);
    }

    /**
     * Method to create all the bench players
     */
    private void generateBenchPlayers() {
        TextView benchLabel = findViewById(R.id.benchPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());

        //only add players too bench when there are players to add
        if (numBenchPlayers > 0) {

            //layout where all the benchPlayerButtons get placed
            LinearLayout layout = findViewById(R.id.benchPlayers);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

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
                toggleButton.setMinWidth(100);
                toggleButton.setMinHeight(100);
                //first five players are initially on court, so start at 6th player
                //set the text of the button
                setText(toggleButton, "\n" + game.getHuman(i + 5).getPlayerNumber() + "\n\n" + game.getHuman(i + 5).getName());

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

    /**
     * Method to create all the court player buttons
     */
    private void generateCourtPlayers() {
        TextView benchLabel = findViewById(R.id.courtPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());

        //layout where all the court players get placed
        LinearLayout layout = findViewById(R.id.courtPlayers);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //generate 5 court buttons
        for (int i = 0; i < numCourtPlayers; i++) {

            //make a new toggle button(either on or off)
            ToggleButton toggleButton = new ToggleButton(this);
            //set the size of the toggle button
            toggleButton.setLayoutParams(params);
            //get and set id for each of the 5 court players
            int id = this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName());
            toggleButton.setId(id);
            //text location on the button
            toggleButton.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            toggleButton.setMinWidth(100);
            toggleButton.setMinHeight(100);
            //set text padding and text of the button
            toggleButton.setPadding(3, 3, 3, 3);
            setText(toggleButton, "\n" + game.getHuman(i).getPlayerNumber() + "\n\n" + game.getHuman(i).getName());

            //add it to the linear layout view
            layout.addView(toggleButton);
            //add the on click listener
            toggleButton.setOnClickListener(this);
            //default to not being selected
            toggleButton.setChecked(false);

            //add to array for easy manipulation
            courtPlayerButtons[i] = toggleButton;
        }
        checkButton(courtPlayerButtons[0]);
    }

    /**
     * Method used to substitute players
     * Note that the abstract players array in the game class always is in the order 1-5 on court, and then bench players afterwards
     *
     * @param bench Player location currently on the bench, who will be swapped with the player on the court
     * @param court Player loaction on the court who will be swapped with the player on the bench
     */
    private void substitution(int bench, int court) {
        //the bench starts at index 5 of the player array, so add five to get correct location in array
        //swap the player locations
        AbstractHuman tempBench = game.getHuman(bench + 5);
        game.setHuman(bench + 5, game.getHuman(court));
        game.setHuman(court, tempBench);
        Log.i("myTag", "GAME SORTED: " + game.keepSorted());
        if (game.keepSorted()) {
            sortCourtAndBench();
        }
        //change the text of the bench button to now hold the correct player info
        ToggleButton benchButton = findViewById(this.getResources().getIdentifier("bench" + (bench + 1), "id", this.getPackageName()));
        setText(benchButton, "\n" + game.getHuman(bench + 5).getPlayerNumber() + "\n\n" + game.getHuman(bench + 5).getName());
        //change the text of the court button to hold the substituted player's info
        ToggleButton courtButton = findViewById(this.getResources().getIdentifier("court" + (court + 1), "id", this.getPackageName()));

        setText(courtButton, "\n" + game.getHuman(court).getPlayerNumber() + "\n\n" + game.getHuman(court).getName());
        //deselect the bench button so substitution is no longer happening
        benchButton.setChecked(false);
        benchSelected = false;
        benchSelectedLocation = -1;
    }

    private void sortCourtAndBench() {
        //resort the bench and court players so they are ordered from highest to lowest again
        AbstractHuman[] tempPlayers = game.getPlayers();

        //sort the court
        sort(tempPlayers, 0, 4);

        //sort the bench
        sort(tempPlayers, 5, tempPlayers.length - 1);

        //update the players array in the game
        game.setPlayers(tempPlayers);

        //loop through and update all button text as it could possibly have changed
        for (int i = 0; i < numBenchPlayers; i++) {
            ToggleButton toggleButton = findViewById(getResources().getIdentifier("bench" + (i + 1), "id", getPackageName()));
            setText(toggleButton, "\n" + game.getHuman(i + 5).getPlayerNumber() + "\n\n" + game.getHuman(i + 5).getName());
            //deselect button
            uncheckButton(toggleButton);
        }
        for (int i = 0; i < numCourtPlayers; i++) {
            ToggleButton toggleButton = findViewById(getResources().getIdentifier("court" + (i + 1), "id", getPackageName()));
            setText(toggleButton, "\n" + game.getHuman(i).getPlayerNumber() + "\n\n" + game.getHuman(i).getName());
            uncheckButton(toggleButton);
        }
        //check the first court button
        checkButton(courtPlayerButtons[0]);
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
