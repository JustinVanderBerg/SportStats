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
    private boolean keepBenchSorted;
    private String date;

    /**
     * Primary Constructor
     *
     * @param numPlayers number of players in the game
     */
    public AbstractGame(int numPlayers, long gameLength, boolean keepBenchSorted) {
        this.numPlayers = numPlayers;
        this.players = null;
        this.gameLength = gameLength;
        this.keepBenchSorted = keepBenchSorted;
        date = "";
    }

    /**
     * @return the date the game was played on
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the game
     *
     * @param date date that the game was played on
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return whether the bench should be kept sorted
     */
    public boolean keepSorted() {
        return keepBenchSorted;
    }

    /**
     * @param keepBenchSorted whether the bench should be kept sorted, true for always sorted
     */
    public void setKeepSorted(boolean keepBenchSorted) {
        this.keepBenchSorted = keepBenchSorted;
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

    /**
     * merge sort ascending of the players array
     *
     * @param low  low value of the segment to sort
     * @param high high value of the segment to sort
     */
    public void sortPlayers(int low, int high) {
        //base case
        if (low < high) {

            int middle = (low + high) / 2;
            sortPlayers(low, middle);
            sortPlayers(middle + 1, high);
            merge(players, low, middle, high);
        }
    }

    /**
     * merge sort ascending combine segments
     *
     * @param arr    array to sort
     * @param low    low value of the segment to sort
     * @param middle middle value to split the array around
     * @param high   high value of the segment to sort
     */
    private void merge(AbstractHuman[] arr, int low, int middle, int high) {

        int numLeft = (middle - low + 1);
        int numRight = (high - middle);
        //left segment of array
        AbstractHuman[] L = new BasketballPlayer[numLeft];
        //right segment of array
        AbstractHuman[] R = new BasketballPlayer[numRight];
        //fill the left array with the left section of the array
        for (int i = 0; i < numLeft; i++) {
            L[i] = arr[i + low];
        }
        //fill the right array with the left segment of the array
        for (int i = 0; i < numRight; i++) {
            R[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0, k = low;
        //while there are still elements left in the left and right array
        while (i < numLeft && j < numRight) {
            //if the element in the left array is less than right array set array at current location to left element
            if (L[i].getPlayerNumber() <= R[j].getPlayerNumber()) {
                arr[k] = L[i];
                i++;

            } else {//else the right element is less, therefore set the array a current location to right element

                arr[k] = R[j];
                j++;
            }
            k++;
        }
        //merge the rest of the left segment back into the array, if elements remain
        while (i < numLeft) {
            arr[k] = L[i];
            i++;
            k++;
        }
        //merge the rest of the right segment back into the array, if elements remain
        while (j < numRight) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
