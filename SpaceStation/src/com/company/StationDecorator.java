package com.company;

import java.io.IOException;

public abstract class StationDecorator implements UI {
    protected UI decoratedUI;

    public StationDecorator(UI decoratedUI) {
        this.decoratedUI = decoratedUI;
    }

    public void run() throws InvalidNameException, IOException {
        decoratedUI.run();
    }
    public void simulateBattle(Starfighter ship) {
        decoratedUI.simulateBattle(ship);
    }
}
