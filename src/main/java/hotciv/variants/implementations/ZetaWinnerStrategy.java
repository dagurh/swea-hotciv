package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;

public class ZetaWinnerStrategy implements WinnerStrategy {

    private WinnerStrategy BetaWinnerStrategy, EpsilonWinnerStrategy, currentState;
    private int numberOfRounds;

    public ZetaWinnerStrategy(WinnerStrategy BetaWinnerStrategy, WinnerStrategy EpsilonWinnerStrategy){
        this.BetaWinnerStrategy = BetaWinnerStrategy;
        this.EpsilonWinnerStrategy = EpsilonWinnerStrategy;
        this.currentState = BetaWinnerStrategy;
    }

    @Override
    public Player determineWinner(GameImpl game) {
        return currentState.determineWinner(game);
    }

    @Override
    public void incrementRound(){
        numberOfRounds++;
        if(numberOfRounds == 21){
            currentState = EpsilonWinnerStrategy;
        }
    }
}
