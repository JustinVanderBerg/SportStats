/*Lauren Thomas and Justin VanderBerg
Dec 17, 2018
basketball player class (extends AbstractHuman - ICS4U Final Project Overview
*/

/**
 *
 * @author Lauren
 */

package com.sport.justinandlauren.sportstats;

public class BasketballPlayer extends AbstractHuman{
    //declare variables
    //protected instance variables
    private int shotsAttempted;
    private int shotsMade;
    private double shotsPercent;
    private int threePointsAttempted;
    private int threePointsMade;
    private double threePointsPercent;
    private int foulShotsAttempted;
    private int foulShotsMade;
    private double foulShotPercent;
    private int personalFoulsPerQuarter[];
    private int totalPersonalFouls;
    private int totalTechnicalFouls;
    private double plusMinus;

    /**
     * Primary Constructor
     */
    public BasketballPlayer() {
        super();
        shotsAttempted = 0;
        shotsMade = 0;
        shotsPercent = 0;
        threePointsAttempted = 0;
        threePointsMade = 0;
        threePointsPercent = 0;
        foulShotsAttempted = 0;
        foulShotsMade = 0;
        foulShotPercent = 0;
        personalFoulsPerQuarter = new int[4];
        for (int i = 0; i < 4; i++) {
            personalFoulsPerQuarter[i] = 0;
        }
        totalPersonalFouls = 0;
        totalTechnicalFouls = 0;
        plusMinus = 0;
    }
    
    /**
     * @return the shotsAttempted
     */
    public int getShotsAttempted() {
        return shotsAttempted;
    }

    /**
     * @param shotsAttempted the shotsAttempted to set
     */
    public void setShotsAttempted(int shotsAttempted) {
        this.shotsAttempted = shotsAttempted;
    }

    /**
     * @return the shotsMade
     */
    public int getShotsMade() {
        return shotsMade;
    }

    /**
     * @param shotsMade the shotsMade to set
     */
    public void setShotsMade(int shotsMade) {
        this.shotsMade = shotsMade;
    }

    /**
     * @return the shotsPercent
     */
    public double getShotsPercent() {
        return shotsPercent;
    }

    /**
     * @param shotsPercent the shotsPercent to set
     */
    public void setShotsPercent(double shotsPercent) {
        this.shotsPercent = shotsPercent;
    }

    /**
     * @return the threePointsAttempted
     */
    public int getThreePointsAttempted() {
        return threePointsAttempted;
    }

    /**
     * @param threePointsAttempted the threePointsAttempted to set
     */
    public void setThreePointsAttempted(int threePointsAttempted) {
        this.threePointsAttempted = threePointsAttempted;
    }

    /**
     * @return the threePointsMade
     */
    public int getThreePointsMade() {
        return threePointsMade;
    }

    /**
     * @param threePointsMade the threePointsMade to set
     */
    public void setThreePointsMade(int threePointsMade) {
        this.threePointsMade = threePointsMade;
    }

    /**
     * @return the threePointsPercent
     */
    public double getThreePointsPercent() {
        return threePointsPercent;
    }

    /**
     * @param threePointsPercent the threePointsPercent to set
     */
    public void setThreePointsPercent(double threePointsPercent) {
        this.threePointsPercent = threePointsPercent;
    }

    /**
     * @return the foulShotsAttempted
     */
    public int getFoulShotsAttempted() {
        return foulShotsAttempted;
    }

    /**
     * @param foulShotsAttempted the foulShotsAttempted to set
     */
    public void setFoulShotsAttempted(int foulShotsAttempted) {
        this.foulShotsAttempted = foulShotsAttempted;
    }

    /**
     * @return the foulShotsMade
     */
    public int getFoulShotsMade() {
        return foulShotsMade;
    }

    /**
     * @param foulShotsMade the foulShotsMade to set
     */
    public void setFoulShotsMade(int foulShotsMade) {
        this.foulShotsMade = foulShotsMade;
    }

    /**
     * @return the foulShotPercent
     */
    public double getFoulShotPercent() {
        return foulShotPercent;
    }

    /**
     * @param foulShotPercent the foulShotPercent to set
     */
    public void setFoulShotPercent(double foulShotPercent) {
        this.foulShotPercent = foulShotPercent;
    }

    /**
     * @param index the quarter to get the fouls for
     * @return the personalFouls in the quarter
     */
    public int getPersonalFoulsPerQuarter(int index) {
        return personalFoulsPerQuarter[index];
    }

    /**
     * @param index the quarter of which to set the number of fouls
     * @param
     */
    public void setPersonalFoulsPerQuarter(int index, int numFouls) {
        this.personalFoulsPerQuarter[index] = numFouls;
    }

    /**
     * @return the totalPersonalFouls
     */
    public int getTotalPersonalFouls() {
        return totalPersonalFouls;
    }

    /**
     * @param totalPersonalFouls the totalPersonalFouls to set
     */
    public void setTotalPersonalFouls(int totalPersonalFouls) {
        this.totalPersonalFouls = totalPersonalFouls;
    }

    /**
     * @return the totalTechnicalFouls
     */
    public int getTotalTechnicalFouls() {
        return totalTechnicalFouls;
    }

    /**
     * @param totalTechnicalFouls the totalTechnicalFouls to set
     */
    public void setTotalTechnicalFouls(int totalTechnicalFouls) {
        this.totalTechnicalFouls = totalTechnicalFouls;
    }

    /**
     * @return the plusMinus
     */
    public double getPlusMinus() {
        return plusMinus;
    }

    /**
     * @param plusMinus the plusMinus to set
     */
    public void setPlusMinus(double plusMinus) {
        this.plusMinus = plusMinus;
    }

    @Override
    public String toString() {
        return "BasketballPlayer{" + "shotsAttempted=" + shotsAttempted + ", shotsMade=" + shotsMade + ", shotsPercent=" + shotsPercent + ", threePointsAttempted=" + threePointsAttempted + ", threePointsMade=" + threePointsMade + ", threePointsPercent=" + threePointsPercent + ", foulShotsAttempted=" + foulShotsAttempted + ", foulShotsMade=" + foulShotsMade + ", foulShotPercent=" + foulShotPercent + ", personalFoulsPerQuarter=" + personalFoulsPerQuarter + ", totalPersonalFouls=" + totalPersonalFouls + ", totalTechnicalFouls=" + totalTechnicalFouls + ", plusMinus=" + plusMinus + '}';
    }

    
}
