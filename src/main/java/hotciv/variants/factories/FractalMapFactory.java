package hotciv.variants.factories;

import hotciv.framework.GameObserver;
import hotciv.variants.implementations.*;
import hotciv.variants.interfaces.*;

public class FractalMapFactory implements AbstractFactory {

    @Override
    public ActionStrategy actionStrategy() {
        return new AlphaCivActionStrategy();
    }

    @Override
    public AgeStrategy ageStrategy() {
        return new AlphaCivAgingStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AlphaCivAttackStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new AlphaCivWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new RandomWorldLayoutStrategy();
    }

    @Override
    public GameObserver observer() {
        return new ObserverSpy();
    }

}
