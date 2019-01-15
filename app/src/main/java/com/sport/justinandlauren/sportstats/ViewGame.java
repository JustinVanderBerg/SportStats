package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ViewGame extends AppCompatActivity implements View.OnClickListener {
    private ToggleButton playerButtons[];
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
        int numPlayers = game.getNumPlayers();
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

}
