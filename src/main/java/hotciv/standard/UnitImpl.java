package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String unitType;
    private int defensiveStrength;
    private int attackingStrength;
    private int cost;


    public UnitImpl(String unitType, Player owner) {
        this.owner = owner;
        this.unitType = unitType;
        setDefenceAndAttack();
    }

    public void setDefenceAndAttack(){
        switch (getTypeString()) {
            case GameConstants.ARCHER: {
                attackingStrength = 2;
                defensiveStrength = 3;
                cost = 10;
                }
                break;
            case GameConstants.LEGION: {
                attackingStrength = 4;
                defensiveStrength = 2;
                cost = 15;
                }
                break;
            case GameConstants.SETTLER: {
                attackingStrength = 0;
                defensiveStrength = 3;
                cost = 30;
                }
                break;
        }
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
