package com.company;

import java.io.IOException;

import static com.company.SpaceStationFactory.createSpaceStation;
import static com.company.SpaceStationFactory.createTimedSpaceStation;

//Space station
public class Main {
    public static void main(String[] args) throws InvalidNameException, IOException {

        //uzkuriam stoti su dekoratorium
        UI station = createTimedSpaceStation();
        station.run();

    }
}


