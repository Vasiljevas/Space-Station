package com.company;

import java.io.BufferedWriter;


import static com.company.ReaderWriter.OutputText;


public class FleetCleanerRunnable implements Runnable{
    private BufferedWriter writer;
    private int fleetSize;
    private String shipName;


    public FleetCleanerRunnable(Carrier ship, BufferedWriter writer, double cost) {
        ship.coins -= cost;
        this.shipName = ship.getName();
        this.fleetSize = ship.getFleetSize();
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            long start_time = System.currentTimeMillis();
            //work
            for(int i = 0; i < this.fleetSize; i++) {
                Thread.sleep(20);
            }
            //work finish
            long end_time = System.currentTimeMillis();
            OutputText(this.shipName + "'s fleet of " +  this.fleetSize + " units has been cleaned \n" +
                            "It took " + (end_time-start_time) + " milliseconds to clean the whole fleet\n"
                    , writer);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("The process of cleaning the fleet is over");
        }
    }
}
