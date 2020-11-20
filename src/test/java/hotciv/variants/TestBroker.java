package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.stub.StubGame3;
import hotciv.variants.implementations.broker.GameProxy;
import hotciv.variants.implementations.broker.HotCivGameInvoker;
import hotciv.variants.implementations.broker.LocalMethodClientRequestHandler;
import hotciv.variants.implementations.broker.NullObserver;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;


class TestBroker {

    private Game game;
    private Position pos1_1, pos4_1, pos2_0, pos2_1, pos3_1, pos3_2;

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
}