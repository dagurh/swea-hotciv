package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.Servant;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.variants.implementations.broker.GameProxy;
import hotciv.variants.implementations.broker.HotCivGameInvoker;
import hotciv.variants.implementations.broker.LocalMethodClientRequestHandler;
import hotciv.variants.implementations.broker.NullObserver;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

class TestBroker {

    private GameProxy game;
    private StubGame3 servant;

    @BeforeEach
    void setUp() {
        servant = new StubGame3();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new HotCivGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    public void winnerIsYellow(){
        Player winner = game.getWinner();
        assertThat(winner, is(Player.YELLOW));
    }

    @Test
    public void ageIs42(){
        int age = game.getAge();
        assertThat(age, is(42));
    }

    @Test
    public void playerInTurnIsGreen(){
        Player playerInTurn = game.getPlayerInTurn();
        assertThat(playerInTurn, is(Player.GREEN));
    }

    @Test
    public void moveUnitMovesFrom11To12(){
        Position pos11 = new Position(1,1);
        Position pos12 = new Position(1,2);
        assertThat(game.moveUnit(pos11, pos12), is(true));
    }

    @Test
    public void turnCountsUpWhenEnded(){
        game.endOfTurn();
        assertThat(servant.numberOfEndedTurns, is(1));
    }

    @Test
    public void WorkForceFocusCanBeChanged(){
        Position pos11 = new Position(1,1);
        game.changeWorkForceFocusInCityAt(pos11, "apple");
        assertThat(servant.workForceFocus, is("apple"));
    }



    public class StubGame3 implements Game, Servant {

        private int numberOfEndedTurns = 0;
        private String workForceFocus = "hammer";

        @Override
        public String getTileAt(Position p) {
            return null;
        }

        @Override
        public Unit getUnitAt(Position p) {
            return null;
        }

        @Override
        public City getCityAt(Position p) {
            return null;
        }

        @Override
        public Player getPlayerInTurn() {
            return Player.GREEN;
        }

        @Override
        public Object getWinner() {
            return Player.YELLOW;
        }

        @Override
        public int getAge() {
            return 42;
        }

        @Override
        public boolean moveUnit(Position from, Position to) {
            return true;
        }

        @Override
        public void endOfTurn() {
            numberOfEndedTurns++;
        }

        @Override
        public void changeWorkForceFocusInCityAt(Position p, String balance) {
            workForceFocus = balance;
        }

        @Override
        public void changeProductionInCityAt(Position p, String unitType) {

        }

        @Override
        public void performUnitActionAt(Position p) {

        }

        @Override
        public void addObserver(GameObserver observer) {

        }

        @Override
        public void setTileFocus(Position position) {

        }
    }



}