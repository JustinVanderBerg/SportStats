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
 *
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

    /**
     * Primary Constructor
     *
     * @param numPlayers
     */
    public BasketballGame(int numPlayers, long gameLength, boolean keepBenchSorted) {
        super(numPlayers, gameLength, keepBenchSorted);
    }

    /**
     * @return the pointsFor
     */
    public int getPointsFor() {
        return pointsFor;
    }

    /**
     * @param pointsFor the pointsFor to set
     */
    public void setPointsFor(int pointsFor) {
        this.pointsFor = pointsFor;
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
        this.pointsAgainst = pointsAgainst;
    }

    /**
     * @param i The quarter in which to see the team fouls
     * @return the teamFoulsPerQuarter
     */
    public int getTeamFoulsPerQuarter(int i) {
        return teamFoulsPerQuarter[i];
    }

    /**
     *
     * @param i quarter which to set the number of fouls
     * @param numFouls number of fouls that occured in that quarter
     */
    public void setTeamFoulsPerQuarter(int i, int numFouls) {
        this.teamFoulsPerQuarter[i] = numFouls;
    }

    /**
     * @return the totalTeamFouls
     */
    public int getTotalTeamFouls() {
        return totalTeamFouls;
    }

    /**
     * Update the team fouls in the game based off the fouls per quarter
     */
    public void updateTotalTeamFouls() {
        totalTeamFouls = 0;
        for (int i = 0; i < 4; i++) {
            totalTeamFouls += teamFoulsPerQuarter[i];
        }
    }

    /**
     * @param i The quarter which to see the points in
     * @return the pointsForPerQuarter
     */
    public int getPointsForPerQuarter(int i) {
        return pointsForPerQuarter[i];
    }

    /**
     *
     * @param i quarter which to set the number of fouls
     * @param numPoints number of points for that occured in that quarter
     */
    public void setPointsForPerQuarter(int i, int numPoints) {
        pointsForPerQuarter[i] = numPoints;
    }

    /**
     * @param i quarter to view points against
     * @return the pointsAgainstPerQuarter
     */
    public int getPointsAgainstPerQuarter(int i) {
        return pointsAgainstPerQuarter[i];
    }

    /**
     *
     * @param i quarter which to set the number of points against
     * @param numPoints number of points against that occured in that quarter
     */
    public void setPointsAgainstPerQuarter(int i, int numPoints) {
        pointsAgainstPerQuarter[i] = numPoints;
    }

    /**
     *
     * @return number of quarters
     */
    public int getCurrentQuarter() {
        return currentQuarter;
    }

    /**
     *
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
        this.totalShotsMade = totalShotsMade;
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
        this.shotPercentage = shotPercentage;
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
        this.totalThreePointsAttempted = totalThreePointsAttempted;
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
        this.totalThreePointsMade = totalThreePointsMade;
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
        this.totalFoulShotsAttempted = totalFoulShotsAttempted;
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
        this.totalFoulShotsMade = totalFoulShotsMade;
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
     * @return number of shots attempted in the game
     */
    public int getTotalShotsAttempted() {
        return totalShotsAttempted;
    }

    /**
     * @param totalShotsAttempted set the total shots attempted in the game
     */
    public void setTotalShotsAttempted(int totalShotsAttempted) {
        this.totalShotsAttempted = totalShotsAttempted;
    }

    @Override
    public String toString() {
        return "BasketballGame{" + "pointsFor=" + pointsFor + ", pointsAgainst=" + pointsAgainst + ", teamFoulsPerQuarter=" + Arrays.toString(teamFoulsPerQuarter) + ", totalTeamFouls=" + totalTeamFouls + ", pointsForPerQuarter=" + Arrays.toString(pointsForPerQuarter) + ", pointsAgainstPerQuarter=" + Arrays.toString(pointsAgainstPerQuarter) + ", totalShotsAttempted=" + getTotalShotsAttempted() + ", totalShotsMade=" + totalShotsMade + ", shotPercentage=" + shotPercentage + ", totalThreePointsAttempted=" + totalThreePointsAttempted + ", totalThreePointsMade=" + totalThreePointsMade + ", threePointPercentage=" + threePointPercentage + ", totalFoulShotsAttempted=" + totalFoulShotsAttempted + ", totalFoulShotsMade=" + totalFoulShotsMade + ", foulShotPercentage=" + foulShotPercentage + '}';
    }


}
