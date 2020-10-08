package hotciv.variants;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class TestEpsilonCivFixed {

    private GameImpl game;
    private Position pos3_2, pos4_2, pos4_3, pos4_4;
    private EpsilonCivAttackStrategy epsilon;

    public void callEndOfTurn(int x){
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaCivWinnerStrategy(), new GammaCivActionStrategy(), new AlphaCivWorldLayoutStrategy(), new EpsilonCivAttackStrategy(new FixedDieStrategy()));
        pos3_2 = new Position(3, 2);
        pos4_2 = new Position(4,2);
        pos4_3 = new Position(4, 3);
        pos4_4 = new Position(4,4);

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



}