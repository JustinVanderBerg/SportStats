/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Basketball class used to read all past games from the season. Extends AbstractSport class
 */
package com.sport.justinandlauren.sportstats;
import java.util.ArrayList;

public class Basketball extends AbstractSport{
    
    /**
     * Primary Constructor
     * @param gamesWon
     * @param gamesPlayed
     * @param teamName
     * @param games 
     */
    public Basketball(int gamesWon, int gamesPlayed, String teamName, ArrayList<AbstractGame> games) {
        super(gamesWon,gamesPlayed,teamName,games);
    }
    
    public BasketballGame readPastGames(int i){
        return (BasketballGame) (getGames().get(i));
    }
}
