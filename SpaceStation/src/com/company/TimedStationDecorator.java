package com.company;

import java.io.IOException;

public class TimedStationDecorator extends StationDecorator {

    public TimedStationDecorator(UI decoratedUI) {
        super(decoratedUI);
    }


    @Override
    public void run() throws InvalidNameException, IOException {
        long start = System.nanoTime();
        decoratedUI.run();
        long finish = System.nanoTime();
        System.out.println("Station system has worked for " + (finish - start)/1000000000 + " seconds");
    }
}
