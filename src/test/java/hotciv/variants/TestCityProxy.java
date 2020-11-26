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

    private CityProxy city;
    private Position pos1_1, pos1_2;
    private StubCity servant;

    @BeforeEach
    void setUp() {
        Invoker invoker = new HotCivCityInvoker();

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        city = new CityProxy(requestor);
        servant = new StubCity(Player.GREEN);

        pos1_1 = new Position(1,1);
        pos1_2 = new Position(1,2);
    }

    @Test
    public void cityOwnerIsGreen(){
        assertThat(city.getOwner(), is(Player.GREEN));
    }

}
