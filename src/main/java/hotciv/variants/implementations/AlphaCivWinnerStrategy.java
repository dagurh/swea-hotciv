package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;


public class AlphaCivWinnerStrategy implements WinnerStrategy {

    @Override
    public Player determineWinner(int currentAge, GameImpl game) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}