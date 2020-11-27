package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;

public class CityProxy implements City {

    private final Requestor requestor;
    private final String city_OBJECTID = "the boy who lived, come to die";

    public CityProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public Player getOwner() {
        String getOwner = requestor.sendRequestAndAwaitReply(city_OBJECTID, OperationNames.CITY_GET_OWNER, String.class);
        Player[] players = Player.values();
        for (Player p: players) {
            if (getOwner.equals(p.toString())){
                System.out.println("Owner of this city is: " + p.toString());
                return p;
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return requestor.sendRequestAndAwaitReply(city_OBJECTID, OperationNames.CITY_GET_SIZE, int.class);
    }

    @Override
    public int getTreasury() {
        return requestor.sendRequestAndAwaitReply(city_OBJECTID, OperationNames.CITY_GET_TREASURY, int.class);
    }

    @Override
    public String getProduction() {
        return requestor.sendRequestAndAwaitReply(city_OBJECTID, OperationNames.CITY_GET_PRODUCTION, String.class);
    }

    @Override
    public String getWorkforceFocus() {
        return requestor.sendRequestAndAwaitReply(city_OBJECTID, OperationNames.CITY_GET_WORK_FORCE_FOCUS, String.class);
    }
}
