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
        //check if the number is below 0, and set to 0 if it is
        if(shotsAttempted<0) {
            this.shotsAttempted =  0;
        } else {
            this.shotsAttempted = shotsAttempted;
        }
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
        //check if the number is below 0, and set to 0 if it is
        if(shotsMade<0) {
            this.shotsMade =  0;
        } else {
            this.shotsMade = shotsMade;
        }
    }
//TODO do we want to keep this / what do we wanna do with this?
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
        //check if the number is below 0, and set to 0 if it is
        if(threePointsAttempted<0) {
            this.threePointsAttempted =  0;
        } else {
            this.threePointsAttempted = threePointsAttempted;
        }
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
        //check if the number is below 0, and set to 0 if it is
        if(threePointsMade<0) {
            this.threePointsMade =  0;
        } else {
            this.threePointsMade = threePointsMade;
        }
    }
//TODO do we want to keep this / what do we wanna do with this?
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
        //check if the number is below 0, and set to 0 if it is
        if(foulShotsAttempted<0) {
            this.foulShotsAttempted =  0;
        } else {
            this.foulShotsAttempted = foulShotsAttempted;
        }
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
        //check if the number is below 0, and set to 0 if it is
        if(foulShotsMade<0) {
            this.foulShotsMade =  0;
        } else {
            this.foulShotsMade = foulShotsMade;
        }
    }
//TODO do we want to keep this / what do we wanna do with this?
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
        //check if the number is below 0, and set to 0 if it is
        if(numFouls<0) {
            this.personalFoulsPerQuarter[index] =  0;
        } else {
            this.personalFoulsPerQuarter[index] = numFouls;
        }
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
        //check if the number is below 0, and set to 0 if it is
        if(totalPersonalFouls<0) {
            this.totalPersonalFouls =  0;
        } else {
            this.totalPersonalFouls = totalPersonalFouls;
        }
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
        //check if the number is below 0, and set to 0 if it is
        if(totalTechnicalFouls<0) {
            this.totalTechnicalFouls =  0;
        } else {
            this.totalTechnicalFouls = totalTechnicalFouls;
        }
    }
//TODO do we want to keep this / what do we wanna do with this? (set up calculation)
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
        //check if the number is below -10, and set to -10 if it is, and if it is above 10, and set it to 10 if it is
        if(plusMinus<-10) {
            this.plusMinus = -10;
        }else if(plusMinus>10) {
            this.plusMinus = 10;
        } else {
            this.plusMinus = plusMinus;
        }
    }

    @Override
    public String toString() {
        return "BasketballPlayer{" + "shotsAttempted=" + shotsAttempted + ", shotsMade=" + shotsMade + ", shotsPercent=" + shotsPercent + ", threePointsAttempted=" + threePointsAttempted + ", threePointsMade=" + threePointsMade + ", threePointsPercent=" + threePointsPercent + ", foulShotsAttempted=" + foulShotsAttempted + ", foulShotsMade=" + foulShotsMade + ", foulShotPercent=" + foulShotPercent + ", personalFoulsPerQuarter=" + personalFoulsPerQuarter + ", totalPersonalFouls=" + totalPersonalFouls + ", totalTechnicalFouls=" + totalTechnicalFouls + ", plusMinus=" + plusMinus + '}';
    }

    
}
