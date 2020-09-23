package hotciv.variants;

import hotciv.framework.Player;

public interface WinnerStrategy {
    Player determineWinner(int currentAge);
}
