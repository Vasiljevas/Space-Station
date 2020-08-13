package com.company;

/**
 * Factory for the main {@link SpaceStation} service
 */
public class SpaceStationFactory {
    /**
     * create a SpaceStation service
     * @return new SpaceStation instance
     */
    public static UI createSpaceStation() {
        return new SpaceStation();
    }
    // create station with a timed battle simulation
    public static UI createTimedSpaceStation() {return new TimedStationDecorator(new SpaceStation());}
}
