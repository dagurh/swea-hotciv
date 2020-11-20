package hotciv.variants.implementations.broker;

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
    public City getCityAt(Position p) {
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
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
        return 0;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return false;
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
