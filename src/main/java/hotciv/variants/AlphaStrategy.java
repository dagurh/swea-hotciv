package hotciv.variants;

import hotciv.framework.Player;

public interface AlphaStrategy{
    public int advanceAge(int currentAge);

    public Player determineWinner(int currentAge);
}