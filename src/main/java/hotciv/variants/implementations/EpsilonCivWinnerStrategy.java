package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;

public class EpsilonCivWinnerStrategy implements WinnerStrategy {

    @Override
    public Player determineWinner(GameImpl game) {

        if(game.getBlueAttackWinCounter() == 3) { return Player.BLUE; }
        if(game.getRedAttackWinCounter() == 3) { return Player.RED; }

        return null;
    }

    @Override
    public void incrementRound() {

    }


}
