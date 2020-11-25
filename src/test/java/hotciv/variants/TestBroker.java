package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.stub.StubGame2;
import hotciv.stub.StubGame3;
import hotciv.variants.implementations.broker.GameProxy;
import hotciv.variants.implementations.broker.HotCivGameInvoker;
import hotciv.variants.implementations.broker.LocalMethodClientRequestHandler;
import hotciv.variants.implementations.broker.NullObserver;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

class TestBroker {

    private GameProxy game;

    @BeforeEach
    void setUp() {
        Game servant = new StubGame3();
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
}