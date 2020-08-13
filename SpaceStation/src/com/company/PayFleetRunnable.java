package com.company;

import java.io.BufferedWriter;

import static com.company.ReaderWriter.OutputText;

public class PayFleetRunnable implements Runnable{
    private BufferedWriter writer;
    private int fleetSize;
    private String shipName;
    private double coins;

    public PayFleetRunnable(Carrier ship, BufferedWriter writer, double cost) {
        ship.coins -= cost;
        this.coins = ship.getCoins();
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
                Thread.sleep(10);
            }
            //work finish
            long end_time = System.currentTimeMillis();
            OutputText("Each unit of " + this.shipName + "'s fleet got " + this.coins/this.fleetSize + " of coins \n" +
                            "It took " + (end_time-start_time) + " milliseconds to pay coins to the whole fleet\n"
                    , writer);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("The process of paying the fleet is over");
        }
    }
}
