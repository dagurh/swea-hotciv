package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit {

    private final Requestor requestor;
    private final String UNIT_OBJECTID = "hej";

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, OperationNames.UNIT_GET_TYPE_STRING, String.class);
    }

    @Override
    public Player getOwner() {
        String owner = requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, OperationNames.UNIT_GET_OWNER, String.class);
        Player[] players = Player.values();
        for (Player p : players) {
            if (owner.equals(p.toString())) {
                System.out.println("Owner is: " + p.toString());
                return p;
            }
        }
        return null;
    }

    @Override
    public int getMoveCount() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, OperationNames.UNIT_GET_MOVE_COUNT, int.class);
    }

    @Override
    public int getDefensiveStrength() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, OperationNames.UNIT_GET_DEFENSIVE_STRENGTH, int.class);
    }

    @Override
    public int getAttackingStrength() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, OperationNames.UNIT_GET_ATTACKING_STRENGTH, int.class);
    }
}
