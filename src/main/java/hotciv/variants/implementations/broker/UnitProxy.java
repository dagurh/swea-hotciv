package hotciv.variants.implementations.broker;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit {
    @Override
    public String getTypeString() {
        return null;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
