package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestBetaCiv {

    private Game game;
    private Position pos0_1, pos1_0, pos1_1, pos4_1, pos2_0, pos2_1, pos2_2, pos3_1, pos3_2, pos4_2, pos4_3, pos4_4;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new BetaAging(), new BetaWinner(), new AlphaAction());
        pos0_1 = new Position(0,1);
        pos1_0 = new Position(1,0);
        pos1_1 = new Position(1,1);
        pos2_0 = new Position(2,0);
        pos2_1 = new Position(2,1);
        pos2_2 = new Position(2,2);
        pos3_1 = new Position(3,1);
        pos3_2 = new Position(3,2);
        pos4_1 = new Position(4,1);
        pos4_2 = new Position(4,2);
        pos4_3 = new Position(4,3);
        pos4_4 = new Position(4,4);
    }

    public void callEndOfTurn(int x){
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @Test
    public void advanceAgeShouldChangeAfter40Rounds() {
        assertThat(game.getAge(), is(-4000));
        callEndOfTurn(78); // Between -4000 and -100 age advances 100 per round
        assertThat(game.getAge(), is(-100));
        callEndOfTurn(2); // Between -100 and -1 age advances 99 per round
        assertThat(game.getAge(), is(-1));
        callEndOfTurn(2); // Between -1 and 1 age advances 2 per round
        assertThat(game.getAge(), is(1));
        callEndOfTurn(2); // Between 1 and 50 age advances 49 per round
        assertThat(game.getAge(), is(50));
        callEndOfTurn(68); // Between 50 and 1750 age advances 50 per round
        assertThat(game.getAge(), is(1750));
        callEndOfTurn(12); // Between 1750 and 1900 age advances 25 per round
        assertThat(game.getAge(), is(1900));
        callEndOfTurn(28); // Between 1900 and 1970 age advances 5 per round
        assertThat(game.getAge(), is(1970));
        callEndOfTurn(20); // After 1970 age advances 1 per round
        assertThat(game.getAge(), is(1980));
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
}