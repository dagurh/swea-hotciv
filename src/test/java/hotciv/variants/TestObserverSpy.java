package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.factories.AlphaCivFactory;
import hotciv.variants.implementations.ObserverSpy;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class TestObserverSpy {

    private GameImpl game;
    private Position pos0_1, pos1_0, pos1_1, pos4_1, pos2_0, pos2_1, pos2_2, pos3_1, pos3_2, pos4_2, pos4_3, pos4_4;
    private ObserverSpy observer;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        observer = new ObserverSpy();
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

    public void callEndOfTurn(int x) {
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
            observer.turnEnds(game.getPlayerInTurn(), game.getAge());
        }
    }

    @Test
    public void blueIsInTurnAfterRed(){
        callEndOfTurn(1);
        assertThat(observer.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void ageIsIncrementedByA100YearsEachTurn(){
        callEndOfTurn(2);
        assertThat(observer.getCurrentAge(), is(-3900));
    }

    @Test
    public void WorldMapChangesWhenUnitMoves(){
        game.moveUnit(pos4_3,pos4_4);
        observer.worldChangedAt(pos4_4);
        assertThat(observer.getCurrentPosition(), is(pos4_4));
    }

    @Test
    public void WorldChangesWhenTileIsSelected(){
        observer.tileFocusChangedAt(pos4_3);
        assertThat(observer.getCurrentPosition(), is(pos4_3));
    }

}