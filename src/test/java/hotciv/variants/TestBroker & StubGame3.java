package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.Servant;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubCity;
import hotciv.stub.StubUnit;
import hotciv.variants.implementations.broker.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

class TestBroker {

    private GameProxy game;
    private StubGame3 servant;
    private Position pos1_1, pos1_2, pos2_0, pos5_5;
    private CityProxy city;
    private UnitProxy unit;
    //Test af pipeline

    @BeforeEach
    void setUp() {
        servant = new StubGame3();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);
        Invoker invoker = new RootInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        city = new CityProxy(requestor, null);
        unit = new UnitProxy(requestor, null);
        game.addObserver(nullObserver);

        pos1_1 = new Position(1,1);
        pos1_2 = new Position(1,2);
        pos2_0 = new Position(2,0);
        pos5_5 = new Position(5,5);
    }

    //game tests
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

    @Test
    public void gameIDIsSingleton(){
        assertThat(game.getID(), is("singleton"));
    }

    @Test
    public void thereIsAUnitIn2_0() {
        assertThat(game.getUnitAt(pos2_0).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void thereIsACityAt5_5() {
        assertThat(game.getCityAt(pos5_5).getOwner(), is (Player.RED));
    }

    @Test
    public void tileAtPos1_1IsPlains() {
        assertThat(game.getTileAt(pos1_1), is(GameConstants.PLAINS));
    }

    //City tests
    @Test
    public void cityOwnerIsRed(){
        assertThat(game.getCityAt(pos5_5).getOwner(), is(Player.RED));
    }

    @Test
    public void citySizeIs3(){
        assertThat(game.getCityAt(pos5_5).getSize(), is(3));
    }

    @Test
    public void cityTreasureIs2(){
        assertThat(game.getCityAt(pos5_5).getTreasury(), is(2));
    }

    @Test
    public void productionIsArcher(){
        assertThat(game.getCityAt(pos5_5).getProduction(), is("archer"));
    }

    @Test
    public void workForceFocusIsApple(){
        assertThat(game.getCityAt(pos5_5).getWorkforceFocus(), is("apple"));
    }

    //unit tests
    @Test
    public void UnitTypeIsArcher(){
        assertThat(game.getUnitAt(pos2_0).getTypeString(), is("archer"));
    }

    @Test
    public void UnitOwnerIsRed() {
        assertThat(game.getUnitAt(pos2_0).getOwner(), is(Player.RED));
    }

    @Test
    public void UnitMoveCountIs1(){
        assertThat(game.getUnitAt(pos2_0).getMoveCount(), is(1));
    }

    @Test
    public void UnitDefensiveStrengthIs0() {
        assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(0));
    }

    @Test
    public void unitAttackingStrengthIs0() {
        assertThat(game.getUnitAt(pos2_0).getAttackingStrength(), is(0));
    }


    public class StubGame3 implements Game, Servant {

        private int numberOfEndedTurns = 0;
        private int numberOfUnitActions = 0;
        private String workForceFocus = "hammer";
        private String production = "archer";
        private GameObserver observer;
        private String Game_OBJECTID = "singleton";
        private City city = new StubCity(Player.RED);

        @Override
        public String getID() {
            return Game_OBJECTID;
        }

        @Override
        public String getTileAt(Position p) {
            return "plains";
        }

        @Override
        public Unit getUnitAt(Position p) {
            Unit unit = new StubUnit("archer", Player.RED);
            return unit;
        }

        @Override
        public City getCityAt(Position p) {
            return city;
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