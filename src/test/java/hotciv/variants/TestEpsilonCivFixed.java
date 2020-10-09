package hotciv.variants;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.factories.EpsilonCivFactory;
import hotciv.variants.factories.EpsilonCivTestFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class TestEpsilonCivFixed {

    private GameImpl game;
    private Position pos3_2, pos4_2, pos4_3, pos4_4, pos4_5;

    public void callEndOfTurn(int x){
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @BeforeEach
    void setUp() {
        game = new GameImpl(new EpsilonCivTestFactory());
        pos3_2 = new Position(3, 2);
        pos4_2 = new Position(4,2);
        pos4_3 = new Position(4, 3);
        pos4_4 = new Position(4,4);
        pos4_5 = new Position(4,5);
    }

    @Test
    public void blueLegionAttacksRedSettler(){
        callEndOfTurn(1);
        game.moveUnit(pos3_2, pos4_3);
        assertThat(game.getUnitAt(pos4_3).getOwner(), is(Player.BLUE));
    }

    @Test
    public void blueLegionFailAttacksRedSettlerThatHasSupport(){
        game.addUnit(pos4_2, new UnitImpl("legion", Player.RED));
        game.addUnit(pos4_4, new UnitImpl("archer", Player.RED));
        callEndOfTurn(1);
        game.moveUnit(pos3_2, pos4_3);
        assertThat(game.getUnitAt(pos4_3).getOwner(), is(Player.RED));
    }

    @Test
    public void blueGetsOneWinCountAfterASuccessfullAttack(){
        callEndOfTurn(1);
        game.moveUnit(pos3_2, pos4_3);
        assertThat(game.getBlueAttackWinCounter(), is(1));
    }

    @Test
    public void blueWinsGameAfterThreeSuccessfulAttacks(){
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