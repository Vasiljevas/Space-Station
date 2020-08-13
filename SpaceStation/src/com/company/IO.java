package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IO {
    StealthShip loadInvisible() throws IOException;
    Carrier loadFleetUser() throws IOException;
    Starfighter loadCombatant() throws IOException;
    String loadShipInfo() throws IOException;
    void loadPreviousShip(String line);
    void saveStarfighterToFile(Starfighter ship) throws IOException;
    void saveCarrierToFile(Carrier ship) throws IOException;
    void saveStealthshipToFile(StealthShip ship) throws IOException;
    void saveShipInfo(Spaceship ship) throws IOException;
    void saveImage(byte[] image) throws IOException;

}
