package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RecordBasketballGame extends AppCompatActivity implements View.OnClickListener {
    //new basketball game
    AbstractGame game = new BasketballGame(15,null);
    //5 players on court at all times, therefore number of players on bench is total players subtract 5
    int numBenchPlayers = game.getNumPlayers()-5;
    //buttons for the bench players
    ToggleButton[] benchPlayers = new ToggleButton[numBenchPlayers];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_basketball_game);

        setTitle("Record Game");
        //change bench label text transparency
        TextView benchLabel = findViewById(R.id.benchPrompt);
        benchLabel.setTextColor(benchLabel.getTextColors());

        //layout where all the benchPlayers get placed
        LinearLayout layout = findViewById(R.id.benchPlayers);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
        //generate two benchPlayers
        for(int i =0;i<numBenchPlayers;i++) {
            //make a new toggle button(either on or off)
            ToggleButton toggleButton = new ToggleButton(this);
            //set the size of the toggle button
            toggleButton.setLayoutParams(params);
            //get and set id(possibility of 15 bench players max)
            int id = this.getResources().getIdentifier(("bench" + (i+1)),"id", this.getPackageName());
            toggleButton.setId(id);
            //set text padding and text of the button
            toggleButton.setPadding(3,3,3,3);
            toggleButton.setTextOn("Justin VanderBerg" + (i+1));
            toggleButton.setTextOff("Justin VanderBerg" + (i+1));
            toggleButton.setText("Justin VanderBerg" + (i+1));
            //add it to the linear layout view
            layout.addView(toggleButton);
            //add the on click listener
            toggleButton.setOnClickListener(this);
            //default to not being selected
            toggleButton.setChecked(false);

            //set space between buttons
            toggleButton.setMinHeight(0);
            toggleButton.setMinWidth(0);

            //add to array for easy manipulation
            benchPlayers[i] = toggleButton;
        }
        //default to first bench player being selected
        benchPlayers[0].setChecked(true);
    }

    /**
     * This method is called whenever the user clicks on the screen
     * @param view Current view that the user is on
     */
    public void onClick(View view){
        //only allow one bench player to be selected at once
        int btnClicked = -1;
        //check which button was clicked
        for (int i =0; i< numBenchPlayers; i++){
            if(view.getId() == this.getResources().getIdentifier(("bench" + (i+1)),"id", this.getPackageName())){
                btnClicked = i;
                benchPlayers[i].setChecked(true);
                i = numBenchPlayers;
            }
        }
        //turn off all buttons but the button that was clicked
        for(int i =0; i< btnClicked; i++){
            benchPlayers[i].setChecked(false);
        }
        for(int i =btnClicked + 1; i< numBenchPlayers; i++){
            benchPlayers[i].setChecked(false);
        }

        //if the user clicked on one of the bench players
        if(view.getId() == R.id.bench1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Your Title");

            builder.setMessage("Some message...")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // TODO: handle the OK
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }else if(view.getId() == R.id.bench2){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Your Title");

            builder.setMessage("Clicked second bench")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // TODO: handle the OK
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
