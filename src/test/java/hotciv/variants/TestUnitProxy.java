package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.variants.implementations.broker.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestUnitProxy {

    private UnitProxy unit;

    @BeforeEach
    void setUp() {
        NameService nameService = new HotCivNameService();
        Invoker invoker = new HotCivUnitInvoker(nameService);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        unit = new UnitProxy(requestor, null);
    }

    @Test
    public void UnitTypeIsArcher(){
        assertThat(unit.getTypeString(), is("archer"));
    }

    @Test
    public void UnitOwnerIsGreen() {
        assertThat(unit.getOwner(), is(Player.GREEN));
    }

    @Test
    public void UnitMoveCountIs1(){
        assertThat(unit.getMoveCount(), is(1));
    }

    @Test
    public void UnitDefensiveStrengthIs0() {
        assertThat(unit.getDefensiveStrength(), is(0));
    }

    @Test
    public void unitAttackingStrengthIs0() {
        assertThat(unit.getAttackingStrength(), is(0));
    }
}
