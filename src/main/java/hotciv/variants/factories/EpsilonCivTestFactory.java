package hotciv.variants.factories;

import hotciv.framework.GameObserver;
import hotciv.variants.implementations.*;
import hotciv.variants.interfaces.*;

public class EpsilonCivTestFactory implements AbstractFactory {
    @Override
    public ActionStrategy actionStrategy() {
        return new GammaCivActionStrategy();
    }

    @Override
    public AgeStrategy ageStrategy() {
        return new AlphaCivAgingStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new EpsilonCivAttackStrategy(new FixedDieStrategy());
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new EpsilonCivWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new AlphaCivWorldLayoutStrategy();
    }

    @Override
    public GameObserver observer() {
        return new ObserverSpy();
    }
}
