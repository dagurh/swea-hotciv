package hotciv.variants.implementations;

import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.DieStrategy;

public class FixedDieStrategy implements DieStrategy {
    @Override
    public int determineDie(GameImpl game) {
        return 3;
    }
}
