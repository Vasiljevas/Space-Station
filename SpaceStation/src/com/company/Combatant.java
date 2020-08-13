package com.company;

public interface Combatant {
    /**
     * shoots an asteroid
     */
    void shootAsteroid();
    /**
     * checks engine health
     */
    void checkEngines();

    /**
     * fixes engine health
     */
    void fixEngines();
    int getBattlePower();
    void takeDamage(double damage);
    void scanBattlePower();
}