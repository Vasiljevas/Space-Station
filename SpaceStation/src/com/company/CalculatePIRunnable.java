package com.company;

import java.io.BufferedWriter;


import static com.company.ReaderWriter.OutputText;

public class CalculatePIRunnable implements Runnable {
    private BufferedWriter writer;
    private int cycles;

    public CalculatePIRunnable(Carrier ship, BufferedWriter writer, double cost, int cycles) {
        ship.coins -= cost;
        this.writer = writer;
        this.cycles = cycles;
    }

    @Override
    public void run() {
        try {
            long start_time = System.currentTimeMillis();
            //work
            double pi = 0;
            double s = 1;
            for(int i = 1; i < cycles; i=i+2) {
                pi += s/i;
                s = -s;
            }
            pi = pi * 4;
            //work finish
            long end_time = System.currentTimeMillis();
            OutputText("Pi("+ pi+") calculated with " + this.cycles + " cycles\n" +
                            "It took " + (end_time-start_time) + " milliseconds to calculate pi\n"
                    , writer);
        } finally {
            System.out.println("The process to calculate pi is over");
        }
    }
}
