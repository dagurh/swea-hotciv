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
    private Position pos1_1, pos1_2;

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

        pos1_1 = new Position(1,1);
        pos1_2 = new Position(1,2);
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
        assertThat(game.moveUnit(pos1_1, pos1_2), is(true));
    }

    @Test
    public void turnCountsUpWhenEnded(){
        game.endOfTurn();
        assertThat(servant.numberOfEndedTurns, is(1));
    }

    @Test
    public void WorkForceFocusCanBeChanged(){
        game.changeWorkForceFocusInCityAt(pos1_1, "apple");
        assertThat(servant.workForceFocus, is("apple"));
    }

    @Test
    public void ProductionCanBeChanged(){
        game.changeProductionInCityAt(pos1_1,"legion");
        assertThat(servant.production, is("legion"));
    }

    @Test
    public void unitActionCountsUp(){
        game.performUnitActionAt(pos1_1);
        assertThat(servant.numberOfUnitActions, is(1));
    }



    public class StubGame3 implements Game, Servant {

        private int numberOfEndedTurns = 0;
        private int numberOfUnitActions = 0;
        private String workForceFocus = "hammer";
        private String production = "archer";
        private GameObserver observer;

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
            production = unitType;
        }

        @Override
        public void performUnitActionAt(Position p) {
            numberOfUnitActions++;
        }

        @Override
        public void addObserver(GameObserver observer) {
            this.observer = observer;
        }

        @Override
        public void setTileFocus(Position position) {
            observer.tileFocusChangedAt(position);
        }
    }



}