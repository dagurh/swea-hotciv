package hotciv.variants.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.ActionStrategy;

public class ThetaCivActionStrategy implements ActionStrategy {
    @Override
    public void unitAction(GameImpl game, Position p) {
        if (game.getUnitAt(p).getTypeString().equals(GameConstants.CARAVAN) && game.getCityAt(p) != null) {
            game.addPopulation(p, 2);
            game.removeUnit(p);
        }
    }
}
