package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Map;

public interface WinnerStrategy {
    Player determineWinner(int currentAge, Map<Position, City> cityMap);
}
