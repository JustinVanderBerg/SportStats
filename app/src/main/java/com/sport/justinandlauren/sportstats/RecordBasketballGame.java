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
        Button button = new Button(this);
        button.setTag("bench1");
        button.callOnClick();
        button.setId(R.id.bench1);
        button.setText("Justin");
        LinearLayout layout = findViewById(R.id.benchPlayers);
        layout.addView(button);
        button.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    }

    public void MainMenu(View view){
        Intent intent = new Intent(this,LoadScreen.class);
        startActivity(intent);
    }
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
