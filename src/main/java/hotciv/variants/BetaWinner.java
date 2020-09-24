package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

import java.util.Map;

public class BetaWinner implements WinnerStrategy {

    @Override
    public Player determineWinner(int currentAge, Map<Position, City> cityMap) {
        int redCityCounter = 0;
        int blueCityCounter = 0;
        for (City c : cityMap.values()) {
            CityImpl cityTemp = (CityImpl) c;
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
