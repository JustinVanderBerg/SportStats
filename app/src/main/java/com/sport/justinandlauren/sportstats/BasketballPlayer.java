/*Lauren Thomas and Justin VanderBerg
Dec 17, 2018
basketball player class (extends AbstractHuman - ICS4U Final Project Overview
*/

package com.sport.justinandlauren.sportstats;

import java.text.DecimalFormat;

public class BasketballPlayer extends AbstractHuman{
    //declare variables
    //protected instance variables
    private int shotsAttempted;
    private int shotsMade;
    private int threePointsAttempted;
    private int threePointsMade;
    private int foulShotsAttempted;
    private int foulShotsMade;
    private int totalPersonalFouls;
    private int totalTechnicalFouls;
    private DecimalFormat df = new DecimalFormat("#,#00.0%");
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
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(shotsAttempted == 0 && shotsMade == 0) {
            shotsAttempted = 1;
            shotPercent = (double)shotsMade / (shotsAttempted + shotsMade);
            shotsAttempted = 0;
        } else {
            shotPercent = (double)shotsMade / (shotsAttempted + shotsMade);
        }
        return shotPercent;
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
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(threePointsAttempted == 0 && threePointsMade == 0) {
            threePointsAttempted = 1;
            shotPercent = (double)threePointsMade/(threePointsAttempted+threePointsMade);
            threePointsAttempted = 0;
        } else {
            shotPercent = (double)threePointsMade/(threePointsAttempted+threePointsMade);
        }
        return shotPercent;
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
        //declare local variable
        double shotPercent;
        //avoid divide by 0 error by setting total shots attempted to 1 if it is 0
        if(foulShotsAttempted == 0 && foulShotsMade == 0) {
            foulShotsAttempted = 1;
            shotPercent = (double)foulShotsMade/(foulShotsAttempted + foulShotsMade);
            foulShotsAttempted = 0;
        } else {
            shotPercent = (double)foulShotsMade/(foulShotsAttempted + foulShotsMade);
        }
        return shotPercent;
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
    public boolean setTotalPersonalFouls(int totalPersonalFouls) {
        //check if the number is below 0, and set to 0 if it is
        if(totalPersonalFouls<0) {
            this.totalPersonalFouls =  0;
            return false;
        } else {
            this.totalPersonalFouls = totalPersonalFouls;
            return true;
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
        return "Shots Attempted = " + shotsAttempted + "\nShots Made = " + shotsMade +
                "\nShots Percent = " + df.format(getShotsPercent()) + "\nThree Points Attempted = " + threePointsAttempted +
                "\nThree Points Made = " + threePointsMade + "\nThree Points Percent = " + df.format(getThreePointsPercent()) +
                "\nFoul Shots Attempted = " + foulShotsAttempted + "\nFoul Shots Made = " + foulShotsMade +
                "\nFoul Shot Percent = " + df.format(getFoulShotPercent()) + "\nTotal Personal Fouls = " +
                totalPersonalFouls + "\nTotal Technical Fouls = " + totalTechnicalFouls + "\nPlus/Minus = " + getPlusMinus();
    }

    public String toString1() {
        return "Plus/Minus = " + getPlusMinus() + "\nShots Attempted = " + shotsAttempted +
                "\nThree Points Attempted = " + threePointsAttempted + "\nFoul Shots Attempted = " + foulShotsAttempted;
    }

    public String toString2() {
        return "Total Perconal Fouls = " + totalPersonalFouls + "\nShots Made = " + shotsMade + "\nThree Points Made = " + threePointsMade + "\nFoul Shots Made = " +
                foulShotsMade;
    }

    public String toString3() {
        return "Total Technical Fouls = " + totalTechnicalFouls + "\nShots Percent = " + df.format(getShotsPercent()) + "\nThree Points Percent = " +
                df.format(getThreePointsPercent()) + "\nFoul Shot Percent = " + df.format(getFoulShotPercent());
    }
}
