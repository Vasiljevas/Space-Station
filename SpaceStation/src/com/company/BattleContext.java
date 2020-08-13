package com.company;

public class BattleContext {
    private BattleStrategy strategy;

    public BattleContext(BattleStrategy strategy)
    {
        this.strategy = strategy;
    }
    public double executeAction(Starfighter userFighter, Starfighter enemyFighter)
    {
        return strategy.doBattleMove(userFighter, enemyFighter);
    }
}
