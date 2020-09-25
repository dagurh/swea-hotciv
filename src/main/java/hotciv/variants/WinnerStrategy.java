package hotciv.variants;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;


public interface WinnerStrategy {
    Player determineWinner(int currentAge, GameImpl game);
}
