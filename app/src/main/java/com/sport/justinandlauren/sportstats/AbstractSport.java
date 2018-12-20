/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Abstract class which stores all the info for a team for the season
 */
//INSERT PACKAGE TITLE
package com.sport.justinandlauren.sportstats;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author justin
 */

public class AbstractSport {

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

    @Override
    /**
     * TOO IMPLEMENT
     */
    public String toString() {
        return "AbstractSport{" + "gamesWon=" + gamesWon + ", gamesPlayed=" + gamesPlayed + ", teamName=" + teamName + ", games=" + games.toString() + '}';
    }
    
    

}
