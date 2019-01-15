package com.sport.justinandlauren.sportstats;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecordBasketballGame extends AppCompatActivity implements View.OnClickListener {
    //new basketball game
    private BasketballGame game;
    //5 players on court at all times, therefore number of players on bench is total players subtract 5
    private int numBenchPlayers;
    private final int numCourtPlayers = 5;
    //buttons for the bench players
    private ToggleButton[] benchPlayerButtons;
    private ToggleButton[] courtPlayerButtons;

    //variable to store whether clicking a button adds or subtracts the value
    private boolean negative = false;
    //variable to hold which court player is currently selected
    private int courtPlayerSelectedLocation = 0;
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
        game = (BasketballGame) (getIntent().getSerializableExtra("Game Class"));
        numBenchPlayers = game.getNumPlayers() - 5;
        benchPlayerButtons = new ToggleButton[numBenchPlayers];
        courtPlayerButtons = new ToggleButton[numCourtPlayers];
        //set the current quarter of the game (from 0 - 3 representing quarters 1 through 4)
        game.setCurrentQuarter(0);
        setTitle("Record Game");
        //change bench label text transparency
        generateBenchPlayers();
        generateCourtPlayers();
        Button btnTimer = findViewById(R.id.btnTimer);
        //set the initial start time of the period
        timeLeftInPeriod = game.getGameLength();
        setTimerText(btnTimer, Color.RED);
        //update display info
        updateTextBoxInfo();
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
            gameTime = new CountDownTimer(timeLeftInPeriod, 500) {
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

                    //if less than a second left therefore game is over
                    if (timeLeftInPeriod < 1000) {
                        timeLeftInPeriod = game.getGameLength();
                        game.setCurrentQuarter(game.getCurrentQuarter() + 1);
                        updateTextBoxInfo();
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
     * Method to check which button was clicked on, and update player info
     *
     * @param view view that was clicked on
     */
    private void updatePlayerInfo(View view) {
        //variable to store whether to add or subtract values
        int changeAmount;

        //check if the user has selected the negative button, and change whether to subtract or add other buttons
        if (view.getId() == R.id.btnNegative) {
            switchNegativeState();
        }

        //if it is negative, subtract the amount from the values, if it isn't add the amount to the values
        if (!negative) {
            changeAmount = 1;
        } else {
            changeAmount = -1;
        }
        //check whether the user has a player on the court selected
        //only do button checks if they have a player selected
        if (courtPlayerSelectedLocation != -1) {
            //temp human used to update info
            BasketballPlayer tempHuman = (BasketballPlayer) game.getHuman(courtPlayerSelectedLocation);

            //check which button was clicked
            //Update number of shots made for the player
            if (view.getId() == R.id.btnShotMade) {
                tempHuman.setShotsMade(tempHuman.getShotsMade() + changeAmount);
                game.setTotalShotsMade(game.getTotalShotsMade() + changeAmount);
                game.setPointsForPerQuarter(game.getCurrentQuarter(), game.getPointsForPerQuarter(game.getCurrentQuarter()) + (changeAmount * 2));
                game.setPointsFor(game.getPointsFor() + (changeAmount * 2));
                //Update number of shots attempted for the player
            } else if (view.getId() == R.id.btnShotAttempted) {
                tempHuman.setShotsAttempted(tempHuman.getShotsAttempted() + changeAmount);
                game.setTotalShotsAttempted(game.getTotalShotsAttempted() + changeAmount);
                //Update number of foul shots made for the player
            } else if (view.getId() == R.id.btnFoulShotMade) {
                tempHuman.setFoulShotsMade(tempHuman.getFoulShotsMade() + changeAmount);
                game.setTotalFoulShotsMade(game.getTotalFoulShotsMade() + changeAmount);
                game.setPointsForPerQuarter(game.getCurrentQuarter(), game.getPointsForPerQuarter(game.getCurrentQuarter()) + changeAmount);
                game.setPointsFor(game.getPointsFor() + changeAmount);
                //Update number of foul shots attempted for the player
            } else if (view.getId() == R.id.btnFoulShotAttempted) {
                tempHuman.setFoulShotsAttempted(tempHuman.getFoulShotsAttempted() + changeAmount);
                game.setTotalFoulShotsAttempted(game.getTotalFoulShotsAttempted() + changeAmount);
                //Update number of three points made for the player
            } else if (view.getId() == R.id.btn3PtMade) {
                tempHuman.setThreePointsMade(tempHuman.getThreePointsMade() + changeAmount);
                game.setTotalThreePointsMade(game.getTotalThreePointsMade() + changeAmount);
                game.setPointsForPerQuarter(game.getCurrentQuarter(), game.getPointsForPerQuarter(game.getCurrentQuarter()) + (changeAmount * 3));
                game.setPointsFor(game.getPointsFor() + (changeAmount * 3));
                //update number of three points attempted for the player and in game class
            } else if (view.getId() == R.id.btn3PtAttempted) {
                tempHuman.setThreePointsMade(tempHuman.getThreePointsMade() + changeAmount);
                game.setTotalThreePointsAttempted(game.getTotalThreePointsAttempted() + changeAmount);
                //update the number of personal fouls for a player and the total for the team for the game
            } else if (view.getId() == R.id.btnPFoul) {
                tempHuman.setPersonalFoulsPerQuarter(game.getCurrentQuarter(), tempHuman.getPersonalFoulsPerQuarter(game.getCurrentQuarter()) + changeAmount);
                tempHuman.setTotalPersonalFouls(tempHuman.getTotalPersonalFouls() + changeAmount);
                game.setTeamFoulsPerQuarter(game.getCurrentQuarter(), game.getTeamFoulsPerQuarter(game.getCurrentQuarter()) + changeAmount);
                game.setTotalTeamFouls(game.getTotalTeamFouls() + changeAmount);
                //add a technical foul to the selected player and to the team total
            } else if (view.getId() == R.id.btnTechFoul) {
                tempHuman.setTotalTechnicalFouls(tempHuman.getTotalTechnicalFouls() + changeAmount);
                game.setTotalTechFouls(game.getTotalTechFouls() + changeAmount);
            } else if (view.getId() == R.id.btnAddOppScore) {
                game.setPointsAgainstPerQuarter(game.getCurrentQuarter(), game.getPointsAgainstPerQuarter(game.getCurrentQuarter()) + changeAmount);
                game.setPointsAgainst(game.getPointsAgainst() + changeAmount);
            }
        } else {
            //if no player is selected on the court, the user can still add one to the team technical fouls
            //and add one to the opposing team score
            if (view.getId() == R.id.btnAddOppScore) {
                game.setPointsAgainstPerQuarter(game.getCurrentQuarter(), game.getPointsAgainstPerQuarter(game.getCurrentQuarter()) + changeAmount);
                game.setPointsAgainst(game.getPointsAgainst() + changeAmount);
            } else if (view.getId() == R.id.btnTechFoul) {
                game.setTotalTechFouls(game.getTotalTechFouls() + changeAmount);
            }
        }
    }

    /**
     * Method to switch the negative state if the button is pushed, and change other button colors
     */
    public void switchNegativeState() {
        if (negative == false) {
            //if the negative button is selected and was previously false change to true
            negative = true;
            //change the colour of the buttons to red to show that it is subtracting
            findViewById(R.id.btnShotMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnAddOppScore).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnShotAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnFoulShotMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnFoulShotAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btn3PtMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btn3PtAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnPFoul).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            findViewById(R.id.btnTechFoul).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
        } else if (negative == true) {
            //if the negative button is selected and was previously true change to false
            negative = false;
            //change the colour of the buttons to green to show that it is adding
            findViewById(R.id.btnShotMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnAddOppScore).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnShotAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnFoulShotMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnFoulShotAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btn3PtMade).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btn3PtAttempted).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnPFoul).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            findViewById(R.id.btnTechFoul).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
        }

    }

    /**
     * Method to finish the game
     *
     * @param view view that the user clicked on
     */
    public void endGame(View view) {
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
            //get the date, used to identify games easily
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd-HH:mm");
            game.setDate(dateFormat.format(calendar.getTime()));
            //add the game to the season
            seasonStats.addGame(game);
            //check who won the game, and update the number of games won if the user's team won
            if (game.getPointsFor() > game.getPointsAgainst()) {
                seasonStats.setGamesWon(seasonStats.getGamesWon() + 1);
            }
            //add one to the number of games played in the season
            seasonStats.setGamesPlayed(seasonStats.getGamesPlayed() + 1);
            //try to write the updated season info to the file
            boolean successful = seasonStats.writeToFile(fos);
            //if season stats were successfully stored, show the game stats to the user
            if (successful) {
                Intent intent = new Intent(this, ViewGame.class);
                intent.putExtra("gameLocation", seasonStats.getGame(seasonStats.getGames().size() - 1));
                startActivity(intent);
            } else {
                //game was not successfully stored, show debugging info in the log
                Log.e("WHY", "BOO-HOO");

            }
        }
    }


    /**
     * This method is called whenever the user clicks on a button that uses this click listener(court buttons, player modification buttons)
     *
     * @param view View that the user clicked
     */
    public void onClick(View view) {
        //check the bench and court buttons
        checkToggleButtons(view);
        //check if the user has added values to the players
        updatePlayerInfo(view);
        if (courtPlayerSelectedLocation != -1) {
            changeInfoButtonStates(true);
        } else {
            changeInfoButtonStates(false);
        }
        updateTextBoxInfo();
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
                //check button
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
                courtPlayerSelectedLocation = i;
                //do substitution if one of the bench players is selected
                if (benchSelected) {

                    //make sure that the bench location is valid
                    if (benchSelectedLocation != -1) {
                        substitution(benchSelectedLocation, i);
                        //no court or bench player is currently selected
                        courtPlayerSelectedLocation = -1;
                        benchSelectedLocation = -1;
                        benchSelected = false;
                    }
                } else {
                    //uncheck button if it was deselected by the user
                    //must call uncheck button in order to change text color
                    //if it is currently unselected it means the user clicked an already selected button
                    //therefore uncheck it
                    if (!courtPlayerButtons[i].isChecked()) {
                        uncheckButton(courtPlayerButtons[i]);
                        courtPlayerSelectedLocation = -1;
                    } else {
                        checkButton(courtPlayerButtons[i]);
                        courtPlayerSelectedLocation = i;

                    }
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
        //select the first button as default
        checkButton(courtPlayerButtons[0]);

    }

    /**
     * Method used to substitute players
     * Note that the abstract players array in the game class always is in the order 1-5 on court, and then bench players afterwards
     *
     * @param bench Player location currently on the bench, who will be swapped with the player on the court
     * @param court Player location on the court who will be swapped with the player on the bench
     */
    private void substitution(int bench, int court) {
        //the bench starts at index 5 of the player array, so add five to get correct location in array
        //swap the player locations
        AbstractHuman tempBench = game.getHuman(bench + 5);
        game.setHuman(bench + 5, game.getHuman(court));
        game.setHuman(court, tempBench);
        if (game.keepSorted()) {
            sortCourtAndBench();
        } else {
            //change the text of the bench button to now hold the correct player info
            setText(benchPlayerButtons[bench], "\n" + game.getHuman(bench + 5).getPlayerNumber() + "\n\n" + game.getHuman(bench + 5).getName());
            uncheckButton(benchPlayerButtons[bench]);
            //change the text of the court button to hold the substituted player's info
            setText(courtPlayerButtons[court], "\n" + game.getHuman(court).getPlayerNumber() + "\n\n" + game.getHuman(court).getName());
            uncheckButton(courtPlayerButtons[court]);
        }
    }

    /**
     * Method to sort the court and bench ascending by player number
     */
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
            setText(benchPlayerButtons[i], "\n" + game.getHuman(i + 5).getPlayerNumber() + "\n\n" + game.getHuman(i + 5).getName());
            //deselect button
            uncheckButton(benchPlayerButtons[i]);
        }
        //update the text of all the court buttons
        for (int i = 0; i < numCourtPlayers; i++) {
            setText(courtPlayerButtons[i], "\n" + game.getHuman(i).getPlayerNumber() + "\n\n" + game.getHuman(i).getName());
            //deselect all buttons
            uncheckButton(courtPlayerButtons[i]);
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

    /**
     * Method to enable/disable all the buttons that modify the selected court player's info
     *
     * @param enabled whether the buttons to add info to players should be enabled or disabled
     */
    private void changeInfoButtonStates(boolean enabled) {
        //enable/disable all the buttons that modify player info
        findViewById(R.id.btnShotMade).setEnabled(enabled);
        findViewById(R.id.btnShotAttempted).setEnabled(enabled);
        findViewById(R.id.btnFoulShotMade).setEnabled(enabled);
        findViewById(R.id.btnFoulShotAttempted).setEnabled(enabled);
        findViewById(R.id.btn3PtMade).setEnabled(enabled);
        findViewById(R.id.btn3PtAttempted).setEnabled(enabled);
        findViewById(R.id.btnPFoul).setEnabled(enabled);
    }

    /**
     * Method to update all the text boxes that display information on the record game screen
     */
    private void updateTextBoxInfo() {
        //text format for plus minus
        DecimalFormat playerPM = new DecimalFormat("#,##0.0");
        //if there is a player selected on the court
        if (courtPlayerSelectedLocation != -1) {
            //show correct player fouls
            ((TextView) findViewById(R.id.txtPlayerFoul)).setText(getString(R.string.playerFouls) + " " + game.getHuman(courtPlayerSelectedLocation).getTotalPersonalFouls());
            //show correct player plus/minus
            ((TextView) findViewById(R.id.txtPlayerPM)).setText(getString(R.string.playerPM) + " " + playerPM.format(game.getHuman(courtPlayerSelectedLocation).getPlusMinus()));
        } else {
            //set to default text without any values if no player is selected
            ((TextView) findViewById(R.id.txtPlayerFoul)).setText(getString(R.string.playerFouls));
            ((TextView) findViewById(R.id.txtPlayerPM)).setText(getString(R.string.playerPM));
        }
        //show correct team fouls
        ((TextView) findViewById(R.id.txtTeamFouls)).setText(getString(R.string.ourTeamFouls) + " " + game.getTotalTeamFouls());
        //show the correct score for user team
        ((TextView) findViewById(R.id.txtOurScore)).setText(getString(R.string.ourScore) + " " + game.getPointsFor());
        //show correct score for away team
        ((TextView) findViewById(R.id.txtOppScore)).setText(getString(R.string.oppScore) + " " + game.getPointsAgainst());
        //show the current quarter
        ((TextView) findViewById(R.id.txtCurrentPeriod)).setText(Html.fromHtml("<small>Current Period</small><br/><br/>" + (game.getCurrentQuarter() + 1)));
    }


}
