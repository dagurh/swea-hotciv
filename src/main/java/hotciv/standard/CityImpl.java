package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.UUID;

public class CityImpl implements City {

    private Player owner;
    int money = 0; // variable money
    private String production = "archer";
    private String workForce;
    private int population = 1;
    private String objectID;

    public CityImpl(Player owner) {
        this.owner = owner;
        objectID = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player newOwner){
        owner = newOwner;
    }

    @Override
    public int getSize() {
        return population;
    }

    public void setSize(int addPopulation){
        population += addPopulation;
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
            case GameConstants.CARAVAN:
                if (getTreasury() >= GameConstants.CARAVANCOST) {
                    addTreasury(-GameConstants.CARAVANCOST);
                    return true;
                }
                break;
        }
        return false;
    }

    public String getObjectID() {
        return objectID;
    }
}
