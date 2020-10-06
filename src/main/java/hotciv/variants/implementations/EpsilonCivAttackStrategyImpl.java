package hotciv.variants.implementations;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.AttackStrategy;

public class EpsilonCivAttackStrategyImpl implements AttackStrategy {
    @Override
    public boolean unitBattle(GameImpl game, Position attacker, Position defender) {
        return true;
    }
}
