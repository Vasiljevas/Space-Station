package com.company;
import java.io.Serializable;
//can go into stealth mode
public class StealthShip extends Spaceship implements Invisible, Serializable {

    private int stealthCrystals;
    private boolean isInStealth = false;

    StealthShip(){

    };

    public StealthShip(String name, double currentFuelTankInLitres, double coins, int shipID, int stealthCrystals) {
        super(name, currentFuelTankInLitres, coins, shipID);
        this.stealthCrystals = stealthCrystals;
        this.type = "Invisible";
    }

    public int getStealthCrystals() {
        return stealthCrystals;
    }

    public boolean isInStealth() {
        return isInStealth;
    }

    public void checkTank(){
        if(this.currentFuelTankInLitres > 150.00) {
            System.out.println("Warning! "+this.name+"'s fuel tank overloaded!");
            this.currentFuelTankInLitres = 150.00;
            System.out.println("Fixed "+ this.name+"'s fuel tank at "+ this.currentFuelTankInLitres);
        } else {
            System.out.println(this.name +" has "+this.currentFuelTankInLitres+" litres of fuel!");
        }
    }
    public void leaveStation() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            if (this.currentFuelTankInLitres < 10.0) {
                System.out.println(this.name + " doesn't have enough fuel to lift off");
            } else {
                this.currentFuelTankInLitres -= 10.0;
                this.isInStation = false;
                System.out.println(this.name + " has achieved lift off");
            }
        }
    }
    public void refillFuelTank() {
        double ans;
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
            ans = 0;
        } else {
            if (this.currentFuelTankInLitres == 150.00) {
                System.out.println(name + "'s fuel tank is already full");
                ans = this.currentFuelTankInLitres;
            } else {
                System.out.println("refilling " + this.name + "'s fuel tank");
                while (this.currentFuelTankInLitres < 150.00) {
                    if (this.coins >= SpaceStation.fuelPrice) {
                        this.coins -= SpaceStation.fuelPrice;
                        this.currentFuelTankInLitres += 1;
                        if (this.currentFuelTankInLitres == 150.00) {
                            System.out.println(this.name + "'s tank fully refilled at " + this.currentFuelTankInLitres + " litres");
                            break;
                        }
                    } else {
                        System.out.println("Not enough coins left");
                        System.out.println(this.name + "' tank filled at: " + this.currentFuelTankInLitres + " litres");
                        break;
                    }
                }
                ans = this.currentFuelTankInLitres;
            }
        }
    }
    public boolean toggleStealth() {
        if(!this.isInStealth) {
            if(this.stealthCrystals > 0 && this.currentFuelTankInLitres > 1.0) {
                this.stealthCrystals -= 1;
                this.currentFuelTankInLitres -= 1.0;
                this.isInStealth = true;
                System.out.println(this.name + " turned on stealth mode.");
            } else {
                System.out.println(this.name + " does not have enough resources to go stealth.");
            }
        } else {
            this.isInStealth = false;
            System.out.println(this.name + " turned off stealth mode.");
        }
        return this.isInStealth;
    }
    public int checkStealth() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
            return 0;
        } else {
            System.out.println("Ship has " + this.stealthCrystals + " stealth crystals ");
            if(this.isInStealth) {
                System.out.println("and is in stealth mode");
            } else {
                System.out.println("and is not in stealth mode");
            }
            return this.stealthCrystals;
        }
    }
}
