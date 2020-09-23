package hotciv.variants;

import hotciv.framework.Player;

public class BetaWinner implements WinnerStrategy{

    @Override
    public Player determineWinner(int currentAge) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}