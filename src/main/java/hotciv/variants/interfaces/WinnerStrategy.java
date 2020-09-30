package hotciv.variants.interfaces;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;


public interface WinnerStrategy {
    Player determineWinner(int currentAge, GameImpl game);
}
