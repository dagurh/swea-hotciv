package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.util.UUID;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String unitType;
    private int defensiveStrength;
    private int attackingStrength;
    private int cost;
    private int moveCount;
    private boolean isFortified = false;
    private String objectID;


    public UnitImpl(String unitType, Player owner) {
        this.owner = owner;
        this.unitType = unitType;
        setDefenceAndAttack();
        objectID = UUID.randomUUID().toString();
    }

    public void setDefenceAndAttack(){
        switch (getTypeString()) {
            case GameConstants.ARCHER: {
                attackingStrength = 2;
                defensiveStrength = 3;
                moveCount = 1;
                cost = GameConstants.ARCHERCOST;
                }
                break;
            case GameConstants.LEGION: {
                attackingStrength = 4;
                defensiveStrength = 2;
                moveCount = 1;
                cost = GameConstants.LEGIONCOST;
                }
                break;
            case GameConstants.SETTLER: {
                attackingStrength = 0;
                defensiveStrength = 3;
                moveCount = 1;
                cost = GameConstants.SETTLERCOST;
                }
                break;
            case GameConstants.CARAVAN: {
                attackingStrength = 1;
                defensiveStrength = 4;
                moveCount = 2;
                cost = GameConstants.CARAVANCOST;
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
        return moveCount;
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

    public void setMoveCount(){moveCount = 1;}

    public void decreaseMoveCount() {
        moveCount -= 1;
    }

    public void setDefensiveStrength() {
        if (isFortified) {
            defensiveStrength = 6;
        } else {
            defensiveStrength = 3;
        }
    }

    public boolean getFortified() {
        return isFortified;
    }

    public void setFortified(boolean status) {
        isFortified = status;
    }

    public String getObjectID() {
        return objectID;
    }
}
