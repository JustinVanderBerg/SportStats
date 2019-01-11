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
    private long gameLength;
    boolean keepBenchSorted;
    /**
     * Primary Constructor
     *
     * @param numPlayers number of players in the game
     */
    public AbstractGame(int numPlayers, long gameLength) {
        this.numPlayers = numPlayers;
        this.players = null;
        this.gameLength = gameLength;
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
     * @param index index of player to get
     * @return player at index index
     */
    public AbstractHuman getHuman(int index) {
        return players[index];
    }

    /**
     * Method to change a person in the player array
     *
     * @param index location of player to set
     * @param human player set at index in player array
     */
    public void setHuman(int index, AbstractHuman human) {
        players[index] = human;
    }

    /**
     * Method to change the whole player array to another one
     *
     * @param players array of players to set the game's players to
     */
    public void setPlayers(AbstractHuman[] players) {
        this.players = players;
    }

    public AbstractHuman[] getPlayers() {
        return this.players;
    }

    @Override
    public String toString() {
        return "AbstractGame{" + "numPlayers=" + numPlayers + ", players=" + Arrays.toString(players) + '}';
    }

    /**
     * @return the length of the game
     */
    public long getGameLength() {
        return gameLength;
    }

    /**
     * @param gameLength length of the game in milliseconds
     */
    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }
}
