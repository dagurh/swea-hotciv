package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {

    private final Player owner;
    int money = 0; // variable money
    private String production = "archer";
    private String workForce;

    public CityImpl(Player owner) {
        this.owner = owner;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    // returns the treasury of the city
    @Override
    public int getTreasury() {
        return money;
    }

    public void addTreasury(int amount) {
        money += amount;
    }

    @Override
    public String getProduction() {
        return production;
    }

    public void changeProduction(String unitType) {
        production = unitType;
    }

    @Override
    public String getWorkforceFocus() {
        return workForce;
    }

    public void changeWorkForce(String balance) {
        workForce = balance;
    }

    public boolean canProduceUnit() {
        switch (getProduction()) {
            case GameConstants.ARCHER:
                if (getTreasury() >= GameConstants.ARCHERCOST) {
                    addTreasury(-GameConstants.ARCHERCOST);
                    return true;
                }
                break;
            case GameConstants.LEGION:
                if (getTreasury() >= GameConstants.LEGIONCOST) {
                    addTreasury(-GameConstants.LEGIONCOST);
                    return true;
                }
                break;
            case GameConstants.SETTLER:
                if (getTreasury() >= GameConstants.SETTLERCOST) {
                    addTreasury(-GameConstants.SETTLERCOST);
                    return true;
                }
                break;
        }
        return false;
    }
}
