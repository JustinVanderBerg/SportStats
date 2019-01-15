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
    private int threePointsAttempted;
    private int threePointsMade;
    private int foulShotsAttempted;
    private int foulShotsMade;
    private int personalFoulsPerQuarter[];
    private int totalPersonalFouls;
    private int totalTechnicalFouls;

    /**
     * Primary Constructor
     */
    public BasketballPlayer() {
        super();
        shotsAttempted = 0;
        shotsMade = 0;
        threePointsAttempted = 0;
        threePointsMade = 0;
        foulShotsAttempted = 0;
        foulShotsMade = 0;
        personalFoulsPerQuarter = new int[4];
        for (int i = 0; i < 4; i++) {
            personalFoulsPerQuarter[i] = 0;
        }
        totalPersonalFouls = 0;
        totalTechnicalFouls = 0;
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
    public boolean setShotsAttempted(int shotsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(shotsAttempted<0) {
            this.shotsAttempted =  0;
            return false;
        } else {
            this.shotsAttempted = shotsAttempted;
            return true;
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
    public boolean setShotsMade(int shotsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(shotsMade<0) {
            this.shotsMade =  0;
            return false;
        } else {
            this.shotsMade = shotsMade;
            return true;
        }
    }

    /**
     * @return the shotsPercent
     */
    public double getShotsPercent() {
        return shotsMade / (shotsAttempted + shotsMade);
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
    public boolean setThreePointsAttempted(int threePointsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(threePointsAttempted<0) {
            this.threePointsAttempted =  0;
            return false;
        } else {
            this.threePointsAttempted = threePointsAttempted;
            return true;
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
    public boolean setThreePointsMade(int threePointsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(threePointsMade<0) {
            this.threePointsMade =  0;
            return false;
        } else {
            this.threePointsMade = threePointsMade;
            return true;
        }
    }

    /**
     * @return the threePointsPercent
     */
    public double getThreePointsPercent() {
        return threePointsMade/(threePointsAttempted+threePointsMade);
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
    public boolean setFoulShotsAttempted(int foulShotsAttempted) {
        //check if the number is below 0, and set to 0 if it is
        if(foulShotsAttempted<0) {
            this.foulShotsAttempted =  0;
            return false;
        } else {
            this.foulShotsAttempted = foulShotsAttempted;
            return true;
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
    public boolean setFoulShotsMade(int foulShotsMade) {
        //check if the number is below 0, and set to 0 if it is
        if(foulShotsMade<0) {
            this.foulShotsMade =  0;
            return false;
        } else {
            this.foulShotsMade = foulShotsMade;
            return true;
        }
    }

    /**
     * @return the foulShotPercent
     */
    public double getFoulShotPercent() {
        return foulShotsMade/(foulShotsAttempted+foulShotsMade);
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
    public boolean setTotalTechnicalFouls(int totalTechnicalFouls) {
        //check if the number is below 0, and set to 0 if it is
        if(totalTechnicalFouls<0) {
            this.totalTechnicalFouls =  0;
            return false;
        } else {
            this.totalTechnicalFouls = totalTechnicalFouls;
            return true;
        }
    }

    /**
     * @return the plusMinus
     */
    public double getPlusMinus() {
        //calculate and return plusMinus
        double plusMinus = 0;
        plusMinus = 0 + (shotsMade) + (threePointsMade) + (foulShotsMade * .5) - (foulShotsAttempted * 0.5) - (shotsAttempted * 0.5) - (threePointsAttempted) - (totalPersonalFouls) - (totalTechnicalFouls * 5);
        if(plusMinus<-10) {
            plusMinus = -10;
        }else if(plusMinus>10) {
            plusMinus = 10;
        }
        return plusMinus;
        //check if the number is below -10, and set to -10 if it is, and if it is above 10, and set it to 10 if it is

    }

    @Override
    public String toString() {
        return "BasketballPlayer{" + "shotsAttempted=" + shotsAttempted + ", shotsMade=" + shotsMade + ", shotsPercent=" + (getShotsPercent() * 100) + ", threePointsAttempted=" + threePointsAttempted + ", threePointsMade=" + threePointsMade + ", threePointsPercent=" + (getThreePointsPercent() * 100) + ", foulShotsAttempted=" + foulShotsAttempted + ", foulShotsMade=" + foulShotsMade + ", foulShotPercent=" + (getFoulShotPercent() * 100) + ", personalFoulsPerQuarter=" + personalFoulsPerQuarter + ", totalPersonalFouls=" + totalPersonalFouls + ", totalTechnicalFouls=" + totalTechnicalFouls + ", plusMinus=" + getPlusMinus() + '}';
    }

    
}
