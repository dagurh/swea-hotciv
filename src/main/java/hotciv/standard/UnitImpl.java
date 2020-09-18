package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String unitType;
    private final int defensiveStrength, attackingStrength, cost;


    public UnitImpl(String unitType, Player owner, int defensiveStrength, int attackingStrength, int cost) {
        this.owner = owner;
        this.unitType = unitType;
        this.defensiveStrength = defensiveStrength;
        this.attackingStrength = attackingStrength;
        this.cost = cost;
    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrength;
    }

    public int getCost() {
        return cost;
    }
}
