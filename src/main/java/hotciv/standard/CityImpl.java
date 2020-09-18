package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {

    private final Player owner;
    int money = 0; // variable money
    private String production;
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
        if(getProduction().equals(GameConstants.ARCHER)){
        if(getTreasury() >= GameConstants.ARCHERCOST){
            return true;
        }
        } else if (getProduction().equals(GameConstants.LEGION)){
            if(getTreasury() >= GameConstants.LEGIONCOST){
                return true;
            }
        } else if (getProduction().equals(GameConstants.SETTLER)){
            if(getTreasury() >= GameConstants.SETTLERCOST){
                return true;
            }
        }
        return false;
    }
}
