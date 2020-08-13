package com.company;

import java.io.*;

public class ReaderWriter implements IO{
    public ReaderWriter(){

    }
    public void saveShipInfo(Spaceship ship) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("shipName.csv");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ship.getType() + ",");
            bufferedWriter.write(ship.getName() + ",");
            bufferedWriter.write(ship.getCurrentFuelTankInLitres() + ",");
            bufferedWriter.write(ship.getCoins() + ",");
            bufferedWriter.write(ship.getShipID() + "");
        } catch (IOException ex) {
            System.out.println("Error writing to file");
        } finally {
            assert bufferedWriter != null;
            bufferedWriter.close();
        }

    }
    public void saveStarfighterToFile(Starfighter ship) throws IOException {
        ObjectOutputStream o = null;
        try {
            FileOutputStream f = new FileOutputStream(new File("ships.txt"));
            o = new ObjectOutputStream(f);


            o.writeObject(ship);


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } finally {
            assert o != null;
            o.close();
        }
    }
    public void saveCarrierToFile(Carrier ship) throws IOException {
        ObjectOutputStream o = null;
        try {
            FileOutputStream f = new FileOutputStream(new File("ships.txt"));
            o = new ObjectOutputStream(f);

            o.writeObject(ship);


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } finally {
            assert o != null;
            o.close();
        }
    }
    public void saveStealthshipToFile(StealthShip ship) throws IOException {
        ObjectOutputStream o = null;
        try {
            FileOutputStream f = new FileOutputStream(new File("ships.txt"));
            o = new ObjectOutputStream(f);
            o.writeObject(ship);


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } finally {
            assert o != null;
            o.close();
        }
    }
    public String loadShipInfo() throws IOException {
        String line = "";
        String[] reader = new String[5];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("shipName.csv"));
            line = br.readLine();
            reader = line.split(",");

            System.out.println("Type: " + reader[0] +
                    ", Name: " + reader[1] +
                    ", Fuel: " + reader[2] +
                    ", Coins: " + reader[3] +
                    ", ID: " + reader[4]);
            return reader[0];
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            assert br != null;
            br.close();
        }
        return reader[0];
    }
    public void loadPreviousShip(String line) {
        try {
            switch (line) {
                case "Combatant":
                    Starfighter prevCombatant = loadCombatant();
                    prevCombatant.checkEngines();
                    break;
                case "FleetUser":
                    Carrier prevFleetUser = loadFleetUser();
                    prevFleetUser.checkFleet();
                    break;
                case "Invisible":
                    StealthShip prevInvisible = loadInvisible();
                    prevInvisible.checkStealth();
                    break;
                default:
                    System.out.println("Ship type is wrong");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e + "line is null");
        }
    }

    public Starfighter loadCombatant() throws IOException {
        Starfighter combatant = null;
        ObjectInputStream oi = null;
        try {
            FileInputStream fi = new FileInputStream(new File("ships.txt"));
            oi = new ObjectInputStream(fi);

            combatant = (Starfighter) oi.readObject();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream (combatant)");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            assert oi != null;
            oi.close();
        }
        return combatant;
    }
    public Carrier loadFleetUser() throws IOException {
        Carrier fleetUser = null;
        ObjectInputStream oi = null;
        try {
            FileInputStream fi = new FileInputStream(new File("ships.txt"));
            oi = new ObjectInputStream(fi);

            fleetUser = (Carrier) oi.readObject();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream (FleetUser)");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            assert oi != null;
            oi.close();
        }
        return fleetUser;
    }
    public StealthShip loadInvisible() throws IOException {
        StealthShip invisible = null;
        ObjectInputStream oi = null;
        try {
            FileInputStream fi = new FileInputStream(new File("ships.txt"));
            oi = new ObjectInputStream(fi);

            invisible = (StealthShip) oi.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream (invisible)");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            assert oi != null;
            oi.close();
        }
        return invisible;
    }

    public static synchronized void OutputText(String text, BufferedWriter bw){
        try {
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveImage(byte[] image) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\User\\Desktop\\VU\\1 kursas\\2 semestras\\OOP\\DownloadedPhotos//" + Math.random() + "image.jpg");
        fos.write(image);
        fos.close();
    }
}
