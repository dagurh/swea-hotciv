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

    @BeforeEach
    void setUp() {
        Invoker invoker = new HotCivCityInvoker();

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        city = new CityProxy(requestor);
    }

    @Test
    public void cityOwnerIsGreen(){
        assertThat(city.getOwner(), is(Player.GREEN));
    }

    @Test
    public void citySizeIs3(){
        assertThat(city.getSize(), is(3));
    }

    @Test
    public void cityTreasureIs2(){
        assertThat(city.getTreasury(), is(2));
    }

    @Test
    public void productionIsArcher(){
        assertThat(city.getProduction(), is("archer"));
    }

    @Test
    public void workForceFocusIsApple(){
        assertThat(city.getWorkforceFocus(), is("apple"));
    }

}
