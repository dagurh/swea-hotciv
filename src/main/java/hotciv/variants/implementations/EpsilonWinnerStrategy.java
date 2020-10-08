package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;

public class EpsilonWinnerStrategy implements WinnerStrategy {

    @Override
    public Player determineWinner(int currentAge, GameImpl game) {

        if(game.getBlueAttackWinCounter() == 3) { return Player.BLUE; }
        if(game.getRedAttackWinCounter() == 3) { return Player.RED; }

        return null;
    }
}
