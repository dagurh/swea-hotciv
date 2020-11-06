package hotciv.variants.factories;

import hotciv.utility.AdapterFractalMap;
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

}
