package hotciv.variants.interfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface AttackStrategy {
    void unitBattle(GameImpl game, Position attacker, Position defender);
}
