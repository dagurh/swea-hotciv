package hotciv.variants.implementations;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.WinnerStrategy;

public class BetaCivWinnerStrategy implements WinnerStrategy {

    @Override
    public Player determineWinner(int currentAge, GameImpl game) {
        Position pos1_1 = new Position(1, 1);
        Position pos4_1 = new Position(4, 1);
        if (game.getCityAt(pos1_1).getOwner() == Player.RED
                && game.getCityAt(pos4_1).getOwner() == Player.RED) {
            return Player.RED;
        }
        if (game.getCityAt(pos1_1).getOwner() == Player.BLUE
                && game.getCityAt(pos4_1).getOwner() == Player.BLUE) {
            return Player.BLUE;
        }
        return null;
    }
}
