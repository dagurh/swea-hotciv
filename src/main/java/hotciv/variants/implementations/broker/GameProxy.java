package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.*;

public class GameProxy implements Game {
    private final Requestor requestor;
    private final String Game_OBJECTID = "singleton";
    private GameObserver observer;

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
        String playerInTurn = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_PLAY_IN_TURN, String.class);
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
        String winner = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_WINNER, String.class);
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
        return requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_AGE, int.class);
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_MOVE_UNIT, boolean.class, from, to);
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_END_OF_TURN, null);
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_CHANGE_WORK_FORCE_FOCUS, null, p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_CHANGE_PRODUCTION, null, p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_UNIT_ACTION, null, p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setTileFocus(Position position) {
        observer.tileFocusChangedAt(position);
    }
}
