/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Abstract class which stores all the info for a team for the season
 */
//INSERT PACKAGE TITLE
package com.sport.justinandlauren.sportstats;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author justin
 */

public abstract class AbstractSport implements Serializable {

    private int gamesWon;
    private int gamesPlayed;
    private String teamName;
    ArrayList<AbstractGame> games;
    
    /**
     * Primary Constructor
     * @param gamesWon
     * @param gamesPlayed
     * @param teamName
     * @param games 
     */
    public AbstractSport(int gamesWon, int gamesPlayed, String teamName, ArrayList<AbstractGame> games) {
        this.gamesWon = gamesWon;
        this.gamesPlayed = gamesPlayed;
        this.teamName = teamName;
        this.games = games;
    }

    public void addGame(AbstractGame game) {
        games.add(game);
    }
    /**
     * @return the gamesWon
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * @param gamesWon the gamesWon to set
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * @return the gamesPlayed
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * @param gamesPlayed the gamesPlayed to set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @param file file to write the sport file to
     * @return true if successful, false otherwise
     */
    public boolean writeToFile(File file) {
        boolean successful = true;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

        } catch (IOException e) {
            Log.e("WritingToFile", "Error writing to file: " + e);
            successful = false;
        }
        return successful;
    }

    /**
     * @param file file to to get the game history from
     * @return the game history in the file
     */
    public static AbstractSport getGameFromFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            AbstractSport temp = (AbstractSport) ois.readObject();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            //print error data from log
            Log.e("readGameError", "Error reading game from file:" + e);
        }
        return null;
    }

    /**
     * @return string representation of the class
     */
    @Override
    public String toString() {
        return "AbstractSport{" + "gamesWon=" + gamesWon + ", gamesPlayed=" + gamesPlayed + ", teamName=" + teamName + ", games=" + games.toString() + '}';
    }
    
    

}
