package com.company;

public interface Invisible {
    /**
     * turns on/off stealth mode
     * @return boolean that states is ship invisible or not;
     */
    boolean toggleStealth();

    /**
     * tells if ship is in stealth mode and how many stealth crystals it has
     * @return how many stealth crystals ship has, if 0, then ship might not be in station
     */
    int checkStealth();

}
