package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {

    private final Player owner;
    int money = 0; // variable money

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
    public int getTreasury()
    {
        money += 6;
        return money;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
