package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.Servant;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubCity;
import hotciv.variants.implementations.broker.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

class TestCityProxy {

    private CityProxy game;
    private Position pos1_1, pos1_2;

    @BeforeEach
    void setUp() {
        GameObserver nullObserver = new NullObserver();

        Invoker invoker = new HotCivCityInvoker();

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new CityProxy(requestor);

        pos1_1 = new Position(1,1);
        pos1_2 = new Position(1,2);
    }
}
