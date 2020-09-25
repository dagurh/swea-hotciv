package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.Map;

public class GammaAction implements ActionStrategy {

    GameImpl game;

    @Override
    public void unitAction(Position p) {

        UnitImpl unitType = (UnitImpl) game.getUnitAt(p);

            if (unitType.getTypeString().equals(GameConstants.SETTLER)) {
                CityImpl newCity = new CityImpl(game.getUnitAt(p).getOwner());
                game.getCities().put(p, newCity);
                game.getUnits().remove(p);
            }
            if(unitType.getTypeString().equals(GameConstants.ARCHER)){
                game.changeUnitsDefensiveStrength(p, 2);
        }
    }
}

