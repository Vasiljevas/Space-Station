package com.company;

public class BattleMoveLaserShot implements BattleStrategy {
    @Override
    public double doBattleMove(Starfighter userFighter, Starfighter enemyFighter) {
        if(userFighter.getBattlePower() > enemyFighter.getBattlePower()) {
            return  0.25 * ((double) enemyFighter.getBattlePower() * 100) / userFighter.getBattlePower();
        } else {
            return  0.10 * ((double) enemyFighter.getBattlePower() * 100) / userFighter.getBattlePower() + 1.0;
        }
    }
}
