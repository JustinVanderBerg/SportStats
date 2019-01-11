package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class LoadScreen extends AppCompatActivity {

    private AbstractGame mainGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
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

    //display player stats
    public void displayPlayerStats(View view) {
        Intent intent = new Intent(this, ViewPlayer.class);
        startActivity(intent);
    }

    //display team stats
    public void displayGameStats(View view) {
        Intent intent = new Intent(this, ViewGame.class);
        startActivity(intent);
    }
}
