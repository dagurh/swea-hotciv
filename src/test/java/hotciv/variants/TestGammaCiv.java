package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestGammaCiv {

    private Game game;
    private Position pos2_0, pos4_3;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaCivWinnerStrategy(), new GammaCivActionStrategy(), new AlphaCivWorldLayoutStrategy(), new AlphaCivAttackStrategy());
        pos2_0 = new Position(2, 0);
        pos4_3 = new Position(4, 3);
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