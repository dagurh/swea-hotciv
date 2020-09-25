package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestGammaCiv {

    private Game game;
    private Position pos0_1, pos1_0, pos1_1, pos4_1, pos2_0, pos2_1, pos2_2, pos3_1, pos3_2, pos4_2, pos4_3, pos4_4;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaAging(), new AlphaWinner(), new GammaAction(), new AlphaWorldLayout());
        pos0_1 = new Position(0, 1);
        pos1_0 = new Position(1, 0);
        pos1_1 = new Position(1, 1);
        pos2_0 = new Position(2, 0);
        pos2_1 = new Position(2, 1);
        pos2_2 = new Position(2, 2);
        pos3_1 = new Position(3, 1);
        pos3_2 = new Position(3, 2);
        pos4_1 = new Position(4, 1);
        pos4_2 = new Position(4, 2);
        pos4_3 = new Position(4, 3);
        pos4_4 = new Position(4, 4);
    }

    public void callEndOfTurn(int x) {
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @Test
    public void settlerCanPerformActionAndBuildCity(){
        game.performUnitActionAt(pos4_3);
        assertNull(game.getUnitAt(pos4_3));
        assertThat(game.getCityAt(pos4_3).getOwner(), is(Player.RED));
        assertThat(game.getCityAt(pos4_3).getSize(), is(1));
    }

    @Test
    public void archerCanFortifyAndDoublesInDefStrAndCannotMove(){
        game.performUnitActionAt(pos2_0);
        assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(6));
        assertThat(game.getUnitAt(pos2_0).getMoveCount(), is(0));
    }

    @Test
    public void archerCanCancelHisFortificationByUsingItAgain(){
        game.performUnitActionAt(pos2_0);
        assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(6));
        callEndOfTurn(2);
        game.performUnitActionAt(pos2_0);
        assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(3));
    }
}