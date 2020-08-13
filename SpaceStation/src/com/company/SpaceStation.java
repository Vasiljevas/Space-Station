package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class SpaceStation implements UI {
    public static double fuelPrice = 10; // for 1 fuel
    public static double engineFixingPriceModifier = 50;
    public static String notLocated = "Error! Ship is not located in the station!";
    private ReaderWriter rw = new ReaderWriter();
    @Override
    public void run() throws InvalidNameException, IOException {
        System.out.println("Welcome to the SpaceStation!");
        System.out.println("What is the model of your ship?");
        while(true) {
            int shipModel = shipChoice();
            if(shipModel < -1 || shipModel > 3) {
                System.out.println("Bad input, please try again.");
            } else if(shipModel == -1) {
                String type = rw.loadShipInfo();
                rw.loadPreviousShip(type);
            } else {
                if (shipModel != 0) {
                    String shipName = "";
                    try {
                        shipName = askShipName();
                    } catch (InvalidNameException e) {
                        System.out.println(e.getMessage());
                        shipName = askShipName();
                    }


                    double shipFuel = Math.round((Math.random() * ((250 - 50) + 1)) + 50);        //gauname baka tarp 50-250 kuro
                    double shipCoins = Math.round((Math.random() * ((1000000 - 100) + 1)) + 100); //pinigai
                    int id = (int) (Math.random()*((3-1)+1))+1;
                    int option = 0;
                    Scanner s = new Scanner(System.in);
                    if (shipModel == 1) { //Starfighter
                        double shipEngineHealth = Math.round(Math.random() * (100 + 1));
                        int battlePower = ThreadLocalRandom.current().nextInt(1,1000 + 1);
                        Starfighter ship = new Starfighter(shipName, shipFuel, shipCoins, id, shipEngineHealth, battlePower);
                        while (option != -1) {
                            printBasicOptions();
                            System.out.println("{6} - Shoot asteroid");
                            System.out.println("{7} - Check engines");
                            System.out.println("{8} - Fix engines");
                            System.out.println("{9} - Simulate battle with enemy ship");
                            option = s.nextInt();
                            if(option == 0) {
                                rw.saveStarfighterToFile(ship);
                                rw.saveShipInfo(ship);
                            }
                            else if(option >= 0 && option < 6) useOption(option, ship);
                            else if(option == 6) ship.shootAsteroid();
                            else if(option == 7) ship.checkEngines();
                            else if(option == 8) ship.fixEngines();
                            else if(option == 9) {
                                simulateBattle(ship);
                            }
                        }
                    } else if (shipModel == 2) { //Carrier
                        int shipFleet = (int) (Math.random() * ((1000 - 1) + 1)) + 1;
                        Carrier ship = new Carrier(shipName, shipFuel, shipCoins, id, shipFleet);
                        BufferedWriter bufferedWriter = null;
                        try {
                            FileWriter fileWriter = new FileWriter("threadRESULT.txt");
                            bufferedWriter = new BufferedWriter(fileWriter);
                            while (option != -1) {
                                printBasicOptions();
                                System.out.println("{6} - Check fleet");
                                System.out.println("{7} - Multitask (thread)");
                                option = s.nextInt();
                                if(option == 0) {
                                    rw.saveCarrierToFile(ship);
                                    rw.saveShipInfo(ship);
                                }
                                else if(option >= 0 && option < 6) useOption(option, ship);
                                else if(option == 6) ship.checkFleet();
                                else if(option == 7) ship.multitask(bufferedWriter);
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            assert bufferedWriter != null;
                            bufferedWriter.close();
                        }
                    } else { //Stealthship
                        int shipCrystals = (int) (Math.random() * (100 + 1));
                        StealthShip ship = new StealthShip(shipName, shipFuel, shipCoins, id, shipCrystals);
                        while (option != -1) {
                            printBasicOptions();
                            System.out.println("{6} - Toggle stealth mode");
                            System.out.println("{7} - Check stealth crystals");
                            option = s.nextInt();
                            if(option == 0) {
                                rw.saveStealthshipToFile(ship);
                                rw.saveShipInfo(ship);
                            }
                            else if(option >= 0 && option < 6) useOption(option, ship);
                            else if(option == 6) ship.toggleStealth();
                            else if(option == 7) ship.checkStealth();
                        }
                    }
                }
                System.out.println("Exiting System, have a good day!");
                break;
            }
        }
    }
    public void simulateBattle(Starfighter ship) {
        if (ship.isInStation()) {
            Starfighter enemyShip = new Starfighter("Enemy", 100, 5000, -1, 100, 500);
            ship.scanBattlePower();
            enemyShip.scanBattlePower();
            while (true) {
                //user move
                System.out.println("Choose action:");
                System.out.println("{1} - Laser shot");
                System.out.println("{2} - Barrel roll");
                System.out.println("{3} - Finisher");
                Scanner s = new Scanner(System.in);
                battleEvent(s.nextInt(), ship, enemyShip);
                if(enemyShip.getEngineHealth() <= 0) {
                    System.out.println(enemyShip.getName() + " has been destroyed");
                    break;
                }
                //enemy move
                int enemyInput = (int) (Math.random()*((3-1)+1))+1;
                battleEvent(enemyInput, enemyShip, ship);
                if(ship.getEngineHealth() <= 5) {
                    System.out.println(ship.getName() + "'s engines are low on health, escaping battle!");
                    break;
                }
            }
            System.out.println("Battle finished, " + ship.getName() + " has " + ship.getEngineHealth() + "% health left");
        } else {
            System.out.println("Starfighter has to be in Station to simulate battle");
        }
    }
    void battleEvent(int input, Starfighter user, Starfighter target) {
        if (input == 1) {
            System.out.println(user.getName() + " used laser shot!");
            BattleContext context = new BattleContext(new BattleMoveLaserShot());
            target.takeDamage(context.executeAction(user, target));
        } else if (input == 2) {
            System.out.println(user.getName() + " used barrel role!");
            BattleContext context = new BattleContext(new BattleMoveBarrelRoll());
            target.takeDamage(context.executeAction(user, target));
        } else if (input == 3) {
            System.out.println(user.getName() + " used finisher!");
            BattleContext context = new BattleContext(new BattleMoveFinisher());
            target.takeDamage(context.executeAction(user, target));
        }
    }
    int shipChoice() {
        System.out.println("{0} - Exit the station system.");
        System.out.println("{1} - Starfighter");
        System.out.println("{2} - Carrier");
        System.out.println("{3} - Stealthship");
        System.out.println("{-1} - To show previous spaceship information");
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
    String askShipName() throws InvalidNameException {
        String answer;
            System.out.println("What is the name of your ship?");
            Scanner s = new Scanner(System.in);
            answer = s.nextLine();
            if(answer.indexOf(',') != -1) {
                throw new InvalidNameException();
            }
        return answer;
    }
    void printBasicOptions() {
        System.out.println("What would you like to do?");
        System.out.println("{-1} - Exit system");
        System.out.println("{0} - Save ship info to database");
        System.out.println("{1} - Show user information");
        System.out.println("{2} - Check Fuel Tank");
        System.out.println("{3} - Leave station");
        System.out.println("{4} - Refill fuel tank");
        System.out.println("{5} - Get Back to station");
    }
    void useOption(int option, Spaceship ship) { //polimorfija
        if(option == 1) ship.printInfo();
        else if(option == 2) ship.checkTank();
        else if(option == 3) ship.leaveStation();
        else if(option == 4) ship.refillFuelTank();
        else ship.getBackToStation();
    }
}
