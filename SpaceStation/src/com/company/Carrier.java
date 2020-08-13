package com.company;
import java.io.BufferedWriter;
import java.io.Serializable;
//carries big fleet
public class Carrier extends Spaceship implements FleetUser, Serializable  {

    private int fleetSize;

    Carrier(){

    };

    public Carrier(String name, double currentFuelTankInLitres, double coins,int shipID, int fleetSize) {
        super(name, currentFuelTankInLitres, coins,shipID);
        this.fleetSize = fleetSize;
        this.type = "FleetUser";
    }
    public int getFleetSize(){
        return fleetSize;
    }
    public void checkTank(){
        if(this.currentFuelTankInLitres > 250.00) {
            System.out.println("Warning! "+this.name+"'s fuel tank overloaded!");
            this.currentFuelTankInLitres = 250.00;
            System.out.println("Fixed "+ this.name+"'s fuel tank at "+ this.currentFuelTankInLitres);
        } else {
            System.out.println(this.name +" has "+this.currentFuelTankInLitres+" litres of fuel!");
        }
    }
    public void leaveStation() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            if (this.currentFuelTankInLitres < 20.00) {
                System.out.println(this.name + " doesn't have enough fuel to lift off");
            } else {
                this.currentFuelTankInLitres -= 20.00;
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
            if (this.currentFuelTankInLitres == 250.00) {
                System.out.println(name + "'s fuel tank is already full");
                ans = this.currentFuelTankInLitres;
            } else {
                System.out.println("refilling " + this.name + "'s fuel tank");
                while (this.currentFuelTankInLitres < 250.00) {
                    if (this.coins >= SpaceStation.fuelPrice) {
                        this.coins -= SpaceStation.fuelPrice;
                        this.currentFuelTankInLitres += 1;
                        if (this.currentFuelTankInLitres == 250.00) {
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
    public void checkFleet() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            System.out.println("The fleet consists of " + this.fleetSize + " people.");
        }
    }
    public void multitask(BufferedWriter bw){
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            Thread photoDownload = new Thread(new PhotoDownloaderRunnable(this, bw, 100));
            Thread piCalculator = new Thread(new CalculatePIRunnable(this, bw, 500, 1000000000));
            Thread fleetCleaner = new Thread(new FleetCleanerRunnable(this, bw, getCleaningCost()));
            Thread fleetPayer = new Thread(new PayFleetRunnable(this, bw, getPayoutCost()));

            photoDownload.start();
            piCalculator.start();
            fleetCleaner.start();
            fleetPayer.start();
        }
    }
    int getCleaningCost() {
        return this.fleetSize * 10; // second number is a multiplier for each fleet unit to calc cost
    }
    double getPayoutCost() {
        return this.coins/2;
    }
}
