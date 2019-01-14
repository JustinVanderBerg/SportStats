package com.sport.justinandlauren.sportstats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ViewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);
        setTitle("Game Stats");

    }

    /**
     * @return abstract game
     */
    private AbstractGame readArray() {
        try {
            //TODO finish files
            File file = new File(this.getFilesDir(), "FILE");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            AbstractGame temp = (AbstractGame) ois.readObject();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
