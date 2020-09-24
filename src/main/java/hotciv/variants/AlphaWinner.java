package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Map;

public class AlphaWinner implements WinnerStrategy{

    @Override
    public Player determineWinner(int currentAge, Map<Position, City> cityMap) {
        if(currentAge == -3000){
            return Player.RED;
        }
        return null;
    }
}