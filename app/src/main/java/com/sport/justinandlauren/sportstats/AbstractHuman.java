/*Lauren Thomas and Justin VanderBerg
Dec 17, 2018
abstract human class - ICS4U Final Project Overview
*/

package com.sport.justinandlauren.sportstats;

import java.io.Serializable;

public abstract class AbstractHuman implements Serializable {
    //declare variables
    //protected instance variables
    protected String name;
    protected int playerNumber;
    protected long secondsPlayed;
    private static final long serialVersionUID = 3L;
    
    /**
     * Primary constructor
     */
    public AbstractHuman() {
        name = "";
        playerNumber = 0;
        secondsPlayed = 0;
    }
    
    /**
     * Secondary constructor 
     * 
     * @param name
     * @param playerNumber
     * @param secondsPlayed 
     */
    public AbstractHuman(String name, int playerNumber, long secondsPlayed) {
        this();
        this.name = name;
        this.playerNumber = playerNumber;
        this.secondsPlayed = secondsPlayed;
    }
    
    /**
     * 
     * @return name of human
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return the humans number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    /**
     * 
     * @return the seconds the human has played
     */
    public long getSecondsPlayed() {
        return secondsPlayed;
    }
    
    /**
     *
     * @param name to set humans name to
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param playerNumber to set the humans number to
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    /**
     * 
     * @param sec to set the humans seconds played to
     */
    public void setSecondsPlayed(long sec) {
        this.secondsPlayed = sec;
    }
    
    /**
     *
     * @return a string representation of the class
     */
    public String toString() {
        return "name=" + name + "\nplayer number=" + playerNumber + "\nseconds played=" + secondsPlayed + '\n';
    }
    
}
