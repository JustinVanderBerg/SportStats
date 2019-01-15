package com.sport.justinandlauren.sportstats;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ViewGame extends AppCompatActivity implements View.OnClickListener {
    private ToggleButton playerButtons[];
    private int playerSelectedLocation;
    private int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);
        setTitle("Game Stats");
        AbstractGame game = (AbstractGame) getIntent().getSerializableExtra("gameClass");
        showStats(game);
        generatePlayerButtons((BasketballGame) game);
    }

    public void showStats(AbstractGame game) {
        ((TextView) findViewById(R.id.txtGameOutput)).setText(game.toString());
    }

    public void generatePlayerButtons(BasketballGame game) {
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
                int id = this.getResources().getIdentifier(("bench" + (i + 1)), "id", this.getPackageName());
                toggleButton.setId(id);
                //set text padding and text of the button
                toggleButton.setPadding(3, 3, 3, 3);
                toggleButton.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                toggleButton.setMinWidth(100);
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
     * This method is called whenever the user clicks on a button that uses this click listener(court buttons, player modification buttons)
     *
     * @param view View that the user clicked
     */
    public void onClick(View view) {

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
        for (int i = 0; i < numPlayers; i++) {
            if (view.getId() == this.getResources().getIdentifier(("court" + (i + 1)), "id", this.getPackageName())) {
                btnClicked = i;
                playerSelectedLocation = i;
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

}
