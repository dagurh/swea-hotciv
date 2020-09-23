package hotciv.variants;

import hotciv.framework.Player;

public class AlphaRules implements AlphaStrategy{
    @Override
    public int advanceAge(int currentAge) {
        int yearsPerRound = 100;
        return currentAge + yearsPerRound;
    }

    @Override
    public Player determineWinner(int currentAge) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}
