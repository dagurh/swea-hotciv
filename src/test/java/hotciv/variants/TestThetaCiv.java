package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.factories.GammaCivFactory;
import hotciv.variants.factories.ThetaCivFactory;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestThetaCiv {

    private Game game;
    private Position pos0_7, pos9_6, pos5_5, pos6_5;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new ThetaCivFactory());
        pos0_7 = new Position(0, 7);
        pos9_6 = new Position(9, 6);
        pos5_5 = new Position(5, 5);
        pos6_5 = new Position(6, 5);
    }

    public void callEndOfTurn(int x) {
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    @Test
    public void ThereIsDesertAt0_7(){
        assertThat(game.getTileAt(pos0_7), is(GameConstants.DESERT));
    }

    @Test
    public void CityCanProduceCaravan(){

    }

    @Test
    public void CaravanCosts30TreasuryToProduce(){

    }

    @Test
    public void CaravanHasDefence4AndAttack1(){

    }

    @Test
    public void CaravanCanMoveTwoTiles(){

    }

    @Test
    public void OtherUnitsThanCaravanCannotMoveOnDesertTile(){

    }

    @Test
    public void CaravanCanPopulateAndAddsTwoPopulationToCities(){

    }

    @Test
    public void CaravanCannotPerfomPopulateWhenNotInCity(){

    }



}