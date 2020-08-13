package com.company;

import java.util.Objects;

//simple spaceship
abstract class Spaceship {

    public String name;
    public double coins;
    public double currentFuelTankInLitres;
    public boolean isInStation = true;
    public int shipID;
    public String type;

    Spaceship(){

    };

    public Spaceship(String name, double currentFuelTankInLitres, double coins, int shipID) {
        this.name = name;
        this.currentFuelTankInLitres = currentFuelTankInLitres;
        this.coins = coins;
        this.shipID = shipID;
    }

    public String getName() {
        return name;
    }

    public double getCoins() {
        return coins;
    }

    public double getCurrentFuelTankInLitres() {
        return currentFuelTankInLitres;
    }

    public boolean isInStation() {
        return isInStation;
    }

    public int getShipID() {
        return shipID;
    }

    public String getType() { return type;}

    public abstract void checkTank();
    public abstract void leaveStation();
    public abstract void refillFuelTank();
    public void getBackToStation() {
        if(!this.isInStation) {
            this.isInStation = true;
            System.out.println(this.name + "'s is back in the station");
        } else {
            System.out.println(this.name+" is already in the station");
        }
    }
    public void printInfo() {
        if(this.isInStation) {
            System.out.println("Ship: " + this.name
                             + ",\nFuel left: " + this.currentFuelTankInLitres
                             + ",\ncoins: " + this.coins
                             +",\nSerial number: " + this.shipID);
        }
        else {
            System.out.println(SpaceStation.notLocated);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if (obj instanceof Spaceship) {
            Spaceship test = (Spaceship) obj;
            return test.shipID == this.shipID;
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.shipID);
        return hash;
    }
}
