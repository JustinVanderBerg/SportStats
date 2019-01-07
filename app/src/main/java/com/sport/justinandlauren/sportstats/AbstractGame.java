package com.sport.justinandlauren.sportstats;

import java.io.Serializable;
import java.util.Arrays;

/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Abstract class which stores all the basic info that the app will collect on any sport game
 * This stores the number of players in the game, and an array of the players in the game
 */


/**
 * @author justin
 */
public abstract class AbstractGame implements Serializable {
    //once game is created, cannot change the number of players
    private int numPlayers;
    AbstractHuman[] players;

    /**
     * Primary Constructor
     *
     * @param numPlayers number of players in the game
     */
    public AbstractGame(int numPlayers) {
        this.numPlayers = numPlayers;
        this.players = null;
    }

    /**
     * @return the numPlayers
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Method to get a player who played in the game
     *
     * @param i index of player to get
     * @return player at index i
     */
    public AbstractHuman getAbstractHuman(int i) {
        return players[i];
    }

    public void setPlayers(AbstractHuman[] players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "AbstractGame{" + "numPlayers=" + numPlayers + ", players=" + Arrays.toString(players) + '}';
    }

}
