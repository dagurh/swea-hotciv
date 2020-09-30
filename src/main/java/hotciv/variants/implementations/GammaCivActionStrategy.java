package hotciv.variants.implementations;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.interfaces.ActionStrategy;


public class GammaCivActionStrategy implements ActionStrategy {

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
                    game.changeUnitsDefensiveStrength(p);
                    archerUnit.decreaseMoveCount();
                } else {
                    archerUnit.setFortified(false);
                    game.changeUnitsDefensiveStrength(p);
                    archerUnit.setMoveCount();
                }
        }
    }
}

