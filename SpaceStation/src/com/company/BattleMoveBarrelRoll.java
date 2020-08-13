package com.company;

public class BattleMoveBarrelRoll implements BattleStrategy {
    @Override
    public double doBattleMove(Starfighter userFighter, Starfighter enemyFighter) {
        if(userFighter.getEngineHealth() > enemyFighter.getEngineHealth()) {
            return  25;
        } else {
            return  20;
        }
    }
}
