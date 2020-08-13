package com.company;

public interface BattleStrategy {
    //returns how much damage has been dealt to enemyFighter.
    double doBattleMove(Starfighter userFighter, Starfighter enemyFighter);
}
