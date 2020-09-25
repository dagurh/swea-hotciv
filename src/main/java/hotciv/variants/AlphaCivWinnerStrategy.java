package hotciv.variants;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;


public class AlphaCivWinnerStrategy implements WinnerStrategy{

    @Override
    public Player determineWinner(int currentAge, GameImpl game) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}