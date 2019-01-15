package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);
        setTitle("Game Stats");
        AbstractGame game = (AbstractGame) getIntent().getSerializableExtra("gameClass");
        showStats(game);
    }

    public void showStats(AbstractGame game) {
        ((TextView) findViewById(R.id.txtGameOutput)).setText(game.toString());
    }

}
