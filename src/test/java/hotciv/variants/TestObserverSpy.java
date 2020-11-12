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
    private Position pos0_7, pos3_8, pos5_5, pos6_5, pos8_12, pos9_6, pos9_7, pos9_8, pos9_12;
    private ObserverSpy observer;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        observer = new ObserverSpy();
        pos0_7 = new Position(0, 7);
        pos3_8 = new Position(3,8);
        pos5_5 = new Position(5, 5);
        pos6_5 = new Position(6, 5);
        pos8_12 = new Position(8,12);
        pos9_6 = new Position(9, 6);
        pos9_7 = new Position(9, 7);
        pos9_8 = new Position(9, 8);
        pos9_12 = new Position(9,12);
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



}