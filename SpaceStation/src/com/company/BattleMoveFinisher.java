package com.company;

public class BattleMoveFinisher implements BattleStrategy {
    @Override
    public double doBattleMove(Starfighter userFighter, Starfighter enemyFighter) {
        if(enemyFighter.getEngineHealth() < 20.0 || userFighter.getBattlePower() > enemyFighter.getBattlePower() * 2) {
            return enemyFighter.getEngineHealth();
        } else {
            return  1;
        }
    }
}
