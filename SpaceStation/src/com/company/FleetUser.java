package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

public interface FleetUser {
    /**
     * shows fleet information
     */
    void checkFleet();
    void multitask(BufferedWriter bw);
}
