package hotciv.variants.factories;

import hotciv.framework.GameObserver;
import hotciv.variants.implementations.*;
import hotciv.variants.interfaces.*;

public class SemiCivFactory implements AbstractFactory {
    @Override
    public ActionStrategy actionStrategy() {
        return new GammaCivActionStrategy();
    }

    @Override
    public AgeStrategy ageStrategy() {
        return new BetaCivAgingStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new EpsilonCivAttackStrategy(new RandomDieStrategy());
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new EpsilonCivWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new DeltaCivWorldLayoutStrategy();
    }

    @Override
    public GameObserver observer() {
        return new ObserverSpy();
    }
}
