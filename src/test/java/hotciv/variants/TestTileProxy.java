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

class TestTileProxy {

    private TileProxy tile;

    @BeforeEach
    void setUp() {
        Invoker invoker = new HotCivTileInvoker();

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        tile = new TileProxy(requestor);
    }

    @Test
    public void tileTypeIsPlains(){
        assertThat(tile.getTypeString(), is("plains"));
    }
}
