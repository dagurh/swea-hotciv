package hotciv.variants;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestZetaCiv {

    private GameImpl game;
    private Position pos1_1, pos2_0, pos2_1, pos3_1, pos3_2, pos4_1, pos4_3, pos4_4, pos4_5;

    public void callEndOfTurn(int x){
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaCivAgingStrategy(), new ZetaWinnerStrategy(new BetaCivWinnerStrategy(), new EpsilonWinnerStrategy()), new GammaCivActionStrategy(), new AlphaCivWorldLayoutStrategy(), new EpsilonCivAttackStrategy(new FixedDieStrategy()));
        pos1_1 = new Position(1,1);
        pos2_0 = new Position(2,0);
        pos2_1 = new Position(2,1);
        pos3_1 = new Position(3,1);
        pos3_2 = new Position(3, 2);
        pos4_1 = new Position(4,1);
        pos4_3 = new Position(4, 3);
        pos4_4 = new Position(4,4);
        pos4_5 = new Position(4,5);
    }

    @Test
    public void blueHasAllCitiesAndWins() {
        game.endOfTurn();
        game.moveUnit(pos3_2, pos2_1);
        callEndOfTurn(2);
        game.moveUnit(pos2_1, pos1_1);
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void redHasAllCitiesAndWins() {
        game.moveUnit(pos2_0, pos3_1);
        callEndOfTurn(2);
        game.moveUnit(pos3_1, pos4_1);
        assertThat(game.getWinner(), is (Player.RED));
    }

    @Test
    public void threeSuccessfulAttacksDoesNotWinTheGameBefore20Rounds(){
        callEndOfTurn(1);
        game.moveUnit(pos3_2, pos4_3);

        game.addUnit(pos4_4, new UnitImpl("archer", Player.RED));
        callEndOfTurn(2);
        game.moveUnit(pos4_3, pos4_4);

        game.addUnit(pos4_5, new UnitImpl("archer", Player.RED));
        callEndOfTurn(2);
        game.moveUnit(pos4_4, pos4_5);

        assertNull(game.getWinner());
    }

    @Test
    public void blueWinsGameAfterThreeSuccessfulAttacksAfter20RoundsHavePassed(){
        callEndOfTurn(40);
        callEndOfTurn(1);
        game.moveUnit(pos3_2, pos4_3);

        game.addUnit(pos4_4, new UnitImpl("archer", Player.RED));
        callEndOfTurn(2);
        game.moveUnit(pos4_3, pos4_4);

        game.addUnit(pos4_5, new UnitImpl("archer", Player.RED));
        callEndOfTurn(2);
        game.moveUnit(pos4_4, pos4_5);

        assertThat(game.getWinner(), is(Player.BLUE));
    }


}