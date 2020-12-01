package hotciv.stub;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class StubCity implements City {

    private Player owner;
    private int money = 0;
    private String production;
    private String workforce;
    private int population = 1;

    public StubCity(Player owner) {
        this.owner = owner;
    }

    @Override
    public String getCity_OBJECTID() {
        return null;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public int getTreasury() {
        return 2;
    }

    @Override
    public String getProduction() {
        return "archer";
    }

    @Override
    public String getWorkforceFocus() {
        return "apple";
    }
}
