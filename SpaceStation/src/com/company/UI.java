package com.company;

import java.io.IOException;

/**
 * Service to run the created SpaceStation
 * @author Andrius Vasiljevas, github.com/Vasiljevas
 */
public interface UI {
    /**
     * "Opens up" the SpaceStation and starts giving out options
     */
    void run() throws InvalidNameException, IOException;
    void simulateBattle(Starfighter ship);


}
