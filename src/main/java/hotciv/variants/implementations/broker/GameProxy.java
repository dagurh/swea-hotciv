package hotciv.variants.implementations.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import hotciv.framework.*;

public class GameProxy implements Game {
    private final Requestor requestor;
    private final String Game_OBJECTID = "singleton";

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTileAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position position) {
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        String playerInTurn = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GET_PLAYERINTURN, String.class);
        Player[] players = Player.values();
        for (Player p : players) {
            if (playerInTurn.equals(p.toString())) {
                System.out.println("PlayerInTurn was: " + p.toString());
                return p;

            }
        }
        return null;
    }

    @Override
    public Player getWinner() {
        String winner = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GET_WINNER, String.class);
        Player[] players = Player.values();
        for (Player p: players) {
            if (winner.equals(p.toString())){
                System.out.println("Winner was: " + p.toString());
                return p;
            }
        }
        return null;
    }

    @Override
    public int getAge() {
        int age = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GET_AGE, int.class);
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.MOVE_UNIT, boolean.class, from, to);
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
