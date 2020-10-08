package hotciv.variants.factories;

import hotciv.variants.implementations.*;
import hotciv.variants.interfaces.*;

public class EpsilonCivFactory implements AbstractFactory {
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
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new AlphaCivWorldLayoutStrategy();
    }
}
