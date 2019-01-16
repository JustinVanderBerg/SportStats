package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class LoadScreen extends AppCompatActivity {

    private AbstractGame mainGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
        //check if there are other games stored on this device
        checkSportsClass();
    }

    //Called when user selects the record game button
    public void displayEnterPlayers(View view) {
        final Intent intent = new Intent(this, enterPlayers.class);
        //create input dialog for user to input number of players
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle("Start Game");
        //inputbox
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setMessage("Please enter the number of players who will be playing in this game (must be between 5 and 20 players inclusive)")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            int numPlayers = Integer.parseInt(input.getText().toString());
                            if (numPlayers >= 5 && numPlayers <= 20) {
                                mainGame = new BasketballGame(numPlayers, 0, false);
                                intent.putExtra("Game Class", mainGame);
                                startActivity(intent);
                            }
                        } catch (NumberFormatException e) {
                        }

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    /**
     * Method to check if this device has past games stored on it, and if it doesn't, make one
     * and store it in a file for future use
     */
    public void checkSportsClass() {
        File tempFile = this.getFilesDir();
        String fileNames[] = tempFile.list();
        String sportStorageName = getString(R.string.gameHistoryFilename);
        //loop through file names found and check if any match the title name of our file
        boolean fileFound = false;
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].equals(sportStorageName)) {
                fileFound = true;
            }
        }
        //no past games stored on device, therefore create new game class and write it to a new file
        if (!fileFound) {
            //create a new file for storing all the games
            final File newGame = new File(this.getFilesDir(), sportStorageName);
            //get the user to enter a name for their team
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //set title
            builder.setTitle("Start Game");
            //inputbox
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setMessage("Since this is your first game on this device, please enter your team name")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                //Get the team name
                                String teamName = input.getText().toString();
                                AbstractSport seasonStats = new Basketball(0, 0, teamName, new ArrayList<AbstractGame>());
                                boolean successful = seasonStats.writeToFile(new FileOutputStream(newGame));
                                //if writing to file wasn't successful, write the error to the log
                                if (!successful) {
                                    Log.e("writingClass", "Error writing class to file");
                                }
                            } catch (FileNotFoundException e) {
                                Log.e("errorMakingFile", "Error making the file" + e);
                            }

                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    /**
     * Method to display the game select screen
     * @param view view the user clicked on
     */
    public void displayGameStats(View view) {
        Intent intent = new Intent(this, SelectGame.class);
        startActivity(intent);
    }

    /**
     * Method to display the credits screen
     *
     * @param view view the user clicked on
     */
    public void displayCredits(View view) {
        Intent intent = new Intent(this, ViewCredits.class);
        startActivity(intent);
    }
}
