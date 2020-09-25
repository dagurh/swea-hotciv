package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;

import java.util.Map;

public interface ActionStrategy {
    void unitAction(Position p);
}
