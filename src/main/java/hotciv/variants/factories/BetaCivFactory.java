package hotciv.variants.factories;

import hotciv.framework.GameObserver;
import hotciv.variants.implementations.*;
import hotciv.variants.interfaces.*;

public class BetaCivFactory implements AbstractFactory {
    @Override
    public ActionStrategy actionStrategy() {
        return new AlphaCivActionStrategy();
    }

    @Override
    public AgeStrategy ageStrategy() {
        return new BetaCivAgingStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AlphaCivAttackStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new BetaCivWinnerStrategy();
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
