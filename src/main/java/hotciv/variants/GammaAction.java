package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.Map;

public class GammaAction implements ActionStrategy {

    @Override
    public void unitAction(GameImpl game, Position p) {
            if (game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)) {
                CityImpl newCity = new CityImpl(game.getUnitAt(p).getOwner());
                game.removeUnit(p);
                game.addCity(p, newCity);
            } else if (game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)) {
                UnitImpl archerUnit = (UnitImpl) game.getUnitAt(p);
                if(!archerUnit.getFortified()) {
                    archerUnit.setFortified(true);
                    game.changeUnitsDefensiveStrength(p, 2);
                    archerUnit.decreaseMoveCount();
                } else {
                    archerUnit.setFortified(false);
                    game.changeUnitsDefensiveStrength(p, 1);
                    archerUnit.setMoveCount();
                }
        }
    }
}

