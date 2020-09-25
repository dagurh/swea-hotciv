package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

import java.util.Map;

public class BetaWinner implements WinnerStrategy {

    private Game game;

    @Override
    public Player determineWinner(int currentAge, Map<Position, City> cityMap) {
        int redCityCounter = 0;
        int blueCityCounter = 0;
        for (City c : cityMap.values()) {
            if (c.getOwner().equals(Player.RED)) {
                redCityCounter++;
            } else {
                blueCityCounter++;
            }
        }
        if (redCityCounter == cityMap.size()) {
            return Player.RED;
        } else if (blueCityCounter == cityMap.size()) {
            return Player.BLUE;
        } else {
            return null;
        }
    }
}
