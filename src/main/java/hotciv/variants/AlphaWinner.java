package hotciv.variants;

import hotciv.framework.Player;

public class AlphaWinner implements WinnerStrategy{

    @Override
    public Player determineWinner(int currentAge) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}