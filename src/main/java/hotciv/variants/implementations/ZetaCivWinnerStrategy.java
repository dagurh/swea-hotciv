package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;

public class ZetaCivWinnerStrategy implements WinnerStrategy {

    private WinnerStrategy BetaCivWinnerStrategy, EpsilonCivWinnerStrategy, currentState;
    private int numberOfRounds;

    public ZetaCivWinnerStrategy(WinnerStrategy BetaCivWinnerStrategy, WinnerStrategy EpsilonCivWinnerStrategy){
        this.BetaCivWinnerStrategy = BetaCivWinnerStrategy;
        this.EpsilonCivWinnerStrategy = EpsilonCivWinnerStrategy;
        this.currentState = BetaCivWinnerStrategy;
    }

    @Override
    public Player determineWinner(GameImpl game) {
        return currentState.determineWinner(game);
    }

    @Override
    public void incrementRound(){
        numberOfRounds++;
        if(numberOfRounds == 21){
            currentState = EpsilonCivWinnerStrategy;
        }
    }
}
