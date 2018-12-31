package com.sport.justinandlauren.sportstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RecordBasketballGame extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_basketball_game);
        setTitle("Record Game");
        Button[] buttons = new Button[10];
        //layout where all the buttons get placed
        LinearLayout layout = findViewById(R.id.benchPlayers);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
        //generate two buttons
        for(int i =0;i<10;i++) {
            Button button = new Button(this);
            button.setLayoutParams(params);
            button.setTag("bench1");
            button.callOnClick();
            int id = this.getResources().getIdentifier(("bench" + (i+1)),"id", this.getPackageName());
            button.setId(id);
            button.setText("Justin VanderBerg" + (i+1));
            layout.addView(button);
            button.setOnClickListener(this);
            buttons[i] = button;
        }
    }

    /**
     * This method is called whenever the user clicks on the creen
     * @param view Current view that the user is on
     */
    public void onClick(View view){
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
        }
    }
}
