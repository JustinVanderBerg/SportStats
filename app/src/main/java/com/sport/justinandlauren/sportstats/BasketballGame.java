/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Basketball game class which extends the AbstractGame class. Provides storage for all the data that
 * is collected during a basketball game
 */
package com.sport.justinandlauren.sportstats;

import java.text.DecimalFormat;

public class BasketballGame extends AbstractGame {

    private int pointsFor;
    private int pointsAgainst;
    private int totalTeamFouls;
    private int totalShotsAttempted;
    private int totalShotsMade;
    private int totalThreePointsAttempted;
    private int totalThreePointsMade;
    private int totalFoulShotsAttempted;
    private int totalFoulShotsMade;
    private int currentQuarter;
    private int totalTechFouls;
    private DecimalFormat df = new DecimalFormat("#,#00.0%");

    /**
     * Primary Constructor
     *
     * @param numPlayers      number of players playing in the game
     * @param gameLength      the length of the quarters in milliseconds
     * @param keepBenchSorted Whether to keep the bench sorted throughout the game
     */
    public BasketballGame(int numPlayers, long gameLength, boolean keepBenchSorted) {
        super(numPlayers, gameLength, keepBenchSorted);
        //initialize variables
        pointsFor = 0;
        pointsAgainst = 0;
        totalTeamFouls = 0;
        totalShotsAttempted = 0;
        totalShotsMade = 0;
        totalThreePointsAttempted = 0;
        totalThreePointsMade = 0;
        totalFoulShotsAttempted = 0;
        totalFoulShotsMade = 0;
        currentQuarter = 0;
    }


    /**
     * @return the pointsFor
     */
    public int getPointsFor() {
        return pointsFor;
    }

    /**
     *
     * @param pointsFor
     */
    public void setPointsFor(int pointsFor) {
        //check if the number is below 0, and set to 0 if it is
        if(pointsFor<0) {
            this.pointsFor = 0;
        } else {
            this.pointsFor = pointsFor;
        }
    }

    /**
     * @return the pointsAgainst
     */
    public int getPointsAgainst() {
        return pointsAgainst;
    }

    /**
     * @param pointsAgainst the pointsAgainst to set
     */
    public void setPointsAgainst(int pointsAgainst) {
        //check if the number is below 0, and set to 0 if it is
        if(pointsAgainst<0) {
            this.pointsAgainst = 0;
        } else {
            this.pointsAgainst = pointsAgainst;
        }
    }

    /**
     * @param totalTeamFouls new total team fouls to set
     */
    public void setTotalTeamFouls(int totalTeamFouls) {
        //check if the number is below 0, and set to 0 if it is
        if(totalTeamFouls<0) {
            this.totalTeamFouls = 0;
        } else {
            this.totalTeamFouls = totalTeamFouls;
        }
    }
    /**
     * @return the totalTeamFouls
     */
    public int getTotalTeamFouls() {
        return totalTeamFouls;
    }

    /**
     * @return number of quarters
     */
    public int getCurrentQuarter() {
        return currentQuarter;
    }

    /**
     * @param currentQuarter set the number of quarters
     */
    public void setCurrentQuarter(int currentQuarter) {
        this.currentQuarter = currentQuarter;
    }

    /**
     * @return the totalShotsMade
     */
    public int getTotalShotsMade() {
        return totalShotsMade;
    }

    /**
     * @param totalShotsMade the totalShotsMade to set
     */
    public void setTotalShotsMade(int totalShotsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(totalShotsMade<0) {
            this.totalShotsMade = 0;
        } else {
            this.totalShotsMade = totalShotsMade;
        }
    }

    /**
     * @return the shotPercentage
     */
    public double getShotPercentage() {
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(totalShotsAttempted == 0 && totalShotsMade == 0) {
            totalShotsAttempted = 1;
            shotPercent = (double)totalShotsMade /( totalShotsAttempted+totalShotsMade);
            totalShotsAttempted = 0;
        } else {
            shotPercent = (double)totalShotsMade /( totalShotsAttempted+totalShotsMade);
        }
        return shotPercent;
    }

    /**
     * @return the totalThreePointsAttempted
     */
    public int getTotalThreePointsAttempted() {
        return totalThreePointsAttempted;
    }

    /**
     * @param totalThreePointsAttempted the totalThreePointsAttempted to set
     */
    public void setTotalThreePointsAttempted(int totalThreePointsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(totalThreePointsAttempted<0) {
            this.totalThreePointsAttempted =  0;
        } else {
            this.totalThreePointsAttempted = totalThreePointsAttempted;;
        }
    }

    /**
     * @return the totalThreePointsMade
     */
    public int getTotalThreePointsMade() {
        return totalThreePointsMade;
    }

    /**
     * @param totalThreePointsMade the totalThreePointsMade to set
     */
    public void setTotalThreePointsMade(int totalThreePointsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(totalThreePointsMade<0) {
            this.totalThreePointsMade =  0;
        } else {
            this.totalThreePointsMade = totalThreePointsMade;
        }
    }

    /**
     * @return the threePointPercentage
     */
    public double getThreePointPercentage() {
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(totalThreePointsAttempted == 0 && totalThreePointsMade == 0) {
            totalThreePointsAttempted = 1;
            shotPercent = (double)totalThreePointsMade/(totalThreePointsAttempted + totalThreePointsMade);
            totalThreePointsAttempted = 0;
        } else {
            shotPercent = (double)totalThreePointsMade/(totalThreePointsAttempted + totalThreePointsMade);
        }
        return shotPercent;
    }

    /**
     * @return the totalFoulShotsAttempted
     */
    public int getTotalFoulShotsAttempted() {
        return totalFoulShotsAttempted;
    }

    /**
     * @param totalFoulShotsAttempted the totalFoulShotsAttempted to set
     */
    public void setTotalFoulShotsAttempted(int totalFoulShotsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(totalFoulShotsAttempted<0) {
            this.totalFoulShotsAttempted =  0;
        } else {
            this.totalFoulShotsAttempted = totalFoulShotsAttempted;
        }
    }

    /**
     * @return the totalFoulShotsMade
     */
    public int getTotalFoulShotsMade() {
        return totalFoulShotsMade;
    }

    /**
     * @param totalFoulShotsMade the totalFoulShotsMade to set
     */
    public void setTotalFoulShotsMade(int totalFoulShotsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(totalFoulShotsMade<0) {
            this.totalFoulShotsMade =  0;
        } else {
            this.totalFoulShotsMade = totalFoulShotsMade;
        }
    }

    /**
     * @return the foulShotPercentage
     */
    public double getFoulShotPercentage() {
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(totalFoulShotsAttempted == 0 && totalFoulShotsMade == 0) {
            totalFoulShotsAttempted = 1;
            shotPercent = (double)totalFoulShotsMade/(totalFoulShotsAttempted + totalFoulShotsMade);
            totalFoulShotsAttempted = 0;
        } else {
            shotPercent = (double)totalFoulShotsMade/(totalFoulShotsAttempted + totalFoulShotsMade);
        }
        return shotPercent;
    }

    /**
     * @return total technical fouls in the game
     */
    public int getTotalTechFouls() {
        return totalTechFouls;
    }

    /**
     * @param totalTechFouls new value to set the total technical fouls to
     */
    public void setTotalTechFouls(int totalTechFouls) {
        //check if the number is below 0, and set to 0 if it is
        if(totalTechFouls<0) {
            this.totalTechFouls =  0;
        } else {
            this.totalTechFouls = totalTechFouls;
        }
    }

    /**
     * @return number of shots attempted in the game
     */
    public int getTotalShotsAttempted() {
        return totalShotsAttempted;
    }

    /**
     * @param totalShotsAttempted set the total shots attempted in the game
     */
    public void setTotalShotsAttempted(int totalShotsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(totalShotsAttempted<0) {
            this.totalShotsAttempted =  0;
        } else {
            this.totalShotsAttempted = totalShotsAttempted;
        }
    }

    /**
     * Method that overrides the get human in the abstract game class so that casting isn't required
     *
     * @param index index of player to get
     * @return a basketball player at index i
     */
    @Override
    public BasketballPlayer getHuman(int index) {
        return (BasketballPlayer) players[index];
    }

    @Override
    public String toString() {
        return "Total Points For = " + pointsFor + "\t\t\t\tTotal Points Against = " + pointsAgainst +
                "\t\t\t\tTotal Team Fouls = " + totalTeamFouls + "\nTotal Shots Attempted = " +
                getTotalShotsAttempted() + "\t\t\t\tTotal Shots Made = " + totalShotsMade +
                "\t\t\t\tShot Percentage = " + df.format(getShotPercentage()) + "\nTotal Three Points Attempted = " +
                totalThreePointsAttempted + "\t\t\t\tTotal Three Points Made = " + totalThreePointsMade +
                "\t\t\t\tThree Point Percentage = " + df.format(getThreePointPercentage()) +
                "\nTotal Foul Shots Attempted = " + totalFoulShotsAttempted + "\t\t\t\tTotal Foul Shots Made = " +
                totalFoulShotsMade + "\t\t\t\tFoul Shot Percentage = " + df.format(getFoulShotPercentage());
    }

    public String toString1() {
        return "Total Points For = " + pointsFor + "\nTotal Shots Attempted = " + getTotalShotsAttempted() +
                "\nTotal Three Points Attempted = " + totalThreePointsAttempted + "\nTotal Foul Shots Attempted = " +
                totalFoulShotsAttempted;
    }

    public String toString2() {
        return "Total Points Against = " + pointsAgainst + "\nTotal Shots Made = " + totalShotsMade +
                "\nTotal Three Points Made = " + totalThreePointsMade + "\nTotal Foul Shots Made = " + totalFoulShotsMade;
    }

    public String toString3() {
        return "Total Team Fouls = " + totalTeamFouls + "\nShot Percentage = " + df.format(getShotPercentage()) +
                "\nThree Point Percentage = " + df.format(getThreePointPercentage()) + "\nFoul Shot Percentage = " +
                df.format(getFoulShotPercentage());
    }

}