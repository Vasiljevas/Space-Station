package com.company;
import java.io.Serializable;

//can shoot asteroids
public class Starfighter extends Spaceship implements Combatant, Serializable {

    private double engineHealth; //%
    private int battlePower;

    public Starfighter(String name, double currentFuelTankInLitres, double coins, int shipID, double engineHealth, int battlePower) {
        super(name, currentFuelTankInLitres, coins, shipID);
        this.engineHealth = engineHealth;
        this.battlePower = battlePower;
        this.type = "Combatant";
    }
    public void takeDamage(double damage)
    {
        this.engineHealth -= damage;
        System.out.println(this.getName() + " lost " + damage + "% of health, now at " + this.engineHealth + "%");
    }

    public int getBattlePower() {
        return battlePower;
    }
    public double getEngineHealth() {
        return engineHealth;
    }
    public void checkTank(){
        if(this.currentFuelTankInLitres > 100.00) {
            System.out.println("Warning! "+this.name+"'s fuel tank overloaded!");
            this.currentFuelTankInLitres = 100.00;
            System.out.println("Fixed "+ this.name+"'s fuel tank at "+ this.currentFuelTankInLitres);
        } else {
            System.out.println(this.name +" has "+this.currentFuelTankInLitres+" litres of fuel!");
        }
    }
    public void leaveStation() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            if (this.currentFuelTankInLitres < 5) {
                System.out.println(this.name + " doesn't have enough fuel to lift off");
            } else {
                this.currentFuelTankInLitres -= 5;
                this.isInStation = false;
                System.out.println(this.name + " has achieved lift off");
            }
        }
    }
    public void refillFuelTank() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            if (this.currentFuelTankInLitres == 100.00) {
                System.out.println(name + "'s fuel tank is already full");
            } else {
                System.out.println("refilling " + this.name + "'s fuel tank");
                while (this.currentFuelTankInLitres < 100.00) {
                    if (this.coins >= SpaceStation.fuelPrice) {
                        this.coins -= SpaceStation.fuelPrice;
                        this.currentFuelTankInLitres += 1;
                        if (this.currentFuelTankInLitres == 100.00) {
                            System.out.println(this.name + "'s tank fully refilled at " + this.currentFuelTankInLitres + " litres");
                            break;
                        }
                    } else {
                        System.out.println("Not enough coins left");
                        System.out.println(this.name + "' tank filled at: " + this.currentFuelTankInLitres + " litres");
                        break;
                    }
                }
            }
        }
    }
    public void shootAsteroid() {
        if(this.isInStation) {
            System.out.println("Cannot shoot asteroid while ship is in the station!");
        } else if (this.engineHealth > 2) {
            if(this.engineHealth > 50 && this.currentFuelTankInLitres > 5.0) {
                System.out.println(this.name + " Shot down the asteroid!");
                this.currentFuelTankInLitres -= 5.0;
            }
            else {
                System.out.println(this.name + " has failed to shoot down the asteroid!");
                this.engineHealth -= 2;
                this.currentFuelTankInLitres -= 2.5;
            }
        } else {
            System.out.println(this.name + "'s engines cannot be used to shoot down the asteroid");
        }
    }
    public void checkEngines() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            System.out.println("Ship's Engines at "+this.getEngineHealth()+"%");
        }
    }
    public void scanBattlePower() {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else {
            System.out.println(this.getName() + " power level: " + this.getBattlePower());
        }
    }
    public void fixEngines () {
        if(!this.isInStation) {
            System.out.println(SpaceStation.notLocated);
        } else { if(this.engineHealth < 100) {
            double temp = SpaceStation.engineFixingPriceModifier * (100-engineHealth);
            System.out.println("Fixing "+this.name+"'s engines will cost "+temp+" of coins");
            if(this.coins < temp) {
                System.out.println("Insufficient amount of coins to fix engines");
            } else {
                this.coins -= temp;
                this.engineHealth = 100;
                System.out.println(this.name + "'s engines are back at 100%");
                System.out.println(this.name + " is left with " + this.coins + " of coins");
                }
            }
        }
    }
}

