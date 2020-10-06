package hotciv.variants.implementations;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.AttackStrategy;

public class AlphaCivAttackStrategyImpl implements AttackStrategy {

    @Override
    public void unitBattle(GameImpl game, Position attacker, Position defender) {
        game.moveUnitToNewPos(attacker, defender);
    }
}
