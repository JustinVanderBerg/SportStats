package com.sport.justinandlauren.sportstats;

import java.util.Arrays;

/*
 * Lauren Thomas and Justin VanderBerg
 * December 19, 2018
 * Basketball game class which extends the AbstractGame class. Provides storage for all the data that
 * is collected during a basketball game
 */


//INSERT PACKAGE TITLE

/**
 * @author justin
 */
public class BasketballGame extends AbstractGame {

    private int pointsFor;
    private int pointsAgainst;
    private int teamFoulsPerQuarter[];
    private int totalTeamFouls;
    private int pointsForPerQuarter[];
    private int pointsAgainstPerQuarter[];
    private int totalShotsAttempted;
    private int totalShotsMade;
    private double shotPercentage;
    private int totalThreePointsAttempted;
    private int totalThreePointsMade;
    private double threePointPercentage;
    private int totalFoulShotsAttempted;
    private int totalFoulShotsMade;
    private double foulShotPercentage;
    private int currentQuarter;
    private int totalTechFouls;
    private String date;

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
        teamFoulsPerQuarter = new int[4];
        totalTeamFouls = 0;
        pointsForPerQuarter = new int[4];
        pointsAgainstPerQuarter = new int[4];
        for (int i = 0; i < 4; i++) {
            teamFoulsPerQuarter[i] = 0;
            pointsForPerQuarter[i] = 0;
            pointsAgainstPerQuarter[i] = 0;
        }
        totalShotsAttempted = 0;
        totalShotsMade = 0;
        shotPercentage = 0;
        totalThreePointsAttempted = 0;
        totalThreePointsMade = 0;
        threePointPercentage = 0;
        totalFoulShotsAttempted = 0;
        totalFoulShotsMade = 0;
        foulShotPercentage = 0;
        currentQuarter = 0;
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
        if(pointsAgainst<0) {
            this.pointsAgainst = 0;
        } else {
            this.pointsAgainst = pointsAgainst;
        }
    }

    /**
     * @param i The quarter in which to see the team fouls
     * @return the teamFoulsPerQuarter
     */
    public int getTeamFoulsPerQuarter(int i) {
        return teamFoulsPerQuarter[i];
    }

    /**
     * @param i        quarter which to set the number of fouls
     * @param numFouls number of fouls that occured in that quarter
     */
    public void setTeamFoulsPerQuarter(int i, int numFouls) {
        if(numFouls<0) {
            this.teamFoulsPerQuarter[i] = 0;
        } else {
            this.teamFoulsPerQuarter[i] = numFouls;
        }
    }

    /**
     * @param totalTeamFouls new total team fouls to set
     */
    public void setTotalTeamFouls(int totalTeamFouls) {
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
     * @param i The quarter which to see the points in
     * @return the pointsForPerQuarter
     */
    public int getPointsForPerQuarter(int i) {
        return pointsForPerQuarter[i];
    }

    /**
     * @param i         quarter which to set the number of fouls
     * @param numPoints number of points for that occured in that quarter
     */
    public void setPointsForPerQuarter(int i, int numPoints) {
        if(numPoints<0) {
            pointsForPerQuarter[i] = 0;
        } else {
            pointsForPerQuarter[i] = numPoints;;
        }
    }

    /**
     * @param i quarter to view points against
     * @return the pointsAgainstPerQuarter
     */
    public int getPointsAgainstPerQuarter(int i) {
        return pointsAgainstPerQuarter[i];
    }

    /**
     * @param i         quarter which to set the number of points against
     * @param numPoints number of points against that occured in that quarter
     */
    public void setPointsAgainstPerQuarter(int i, int numPoints) {
        if(numPoints<0) {
            pointsAgainstPerQuarter[i] = 0;
        } else {
            pointsAgainstPerQuarter[i] = numPoints;
        }
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
        return shotPercentage;
    }

    /**
     * @param shotPercentage the shotPercentage to set
     */
    public void setShotPercentage(double shotPercentage) {
        if(shotPercentage<0) {
            this.shotPercentage =  0;
        } else {
            this.shotPercentage = shotPercentage;
        }
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
        return threePointPercentage;
    }

    /**
     * @param threePointPercentage the threePointPercentage to set
     */
    public void setThreePointPercentage(double threePointPercentage) {
        this.threePointPercentage = threePointPercentage;
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
        return foulShotPercentage;
    }

    /**
     * @param foulShotPercentage the foulShotPercentage to set
     */
    public void setFoulShotPercentage(double foulShotPercentage) {
        this.foulShotPercentage = foulShotPercentage;
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
        return "BasketballGame{" + "pointsFor=" + pointsFor + ", pointsAgainst=" + pointsAgainst + ", teamFoulsPerQuarter=" + Arrays.toString(teamFoulsPerQuarter) + ", totalTeamFouls=" + totalTeamFouls + ", pointsForPerQuarter=" + Arrays.toString(pointsForPerQuarter) + ", pointsAgainstPerQuarter=" + Arrays.toString(pointsAgainstPerQuarter) + ", totalShotsAttempted=" + getTotalShotsAttempted() + ", totalShotsMade=" + totalShotsMade + ", shotPercentage=" + shotPercentage + ", totalThreePointsAttempted=" + totalThreePointsAttempted + ", totalThreePointsMade=" + totalThreePointsMade + ", threePointPercentage=" + threePointPercentage + ", totalFoulShotsAttempted=" + totalFoulShotsAttempted + ", totalFoulShotsMade=" + totalFoulShotsMade + ", foulShotPercentage=" + foulShotPercentage + '}';
    }


}
//TODO check if there are unnecessary getters and setters that were automatically generated
//TODO check to make sure I didn't do anything stupid when I added the if statements