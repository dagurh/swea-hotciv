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
    public String getID() {
        return Game_OBJECTID;
    }

    @Override
    public String getTileAt(Position p) {
        return requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_TILE_AT, String.class, p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_UNIT_AT, String.class, p);
        Unit unit = new UnitProxy(requestor, id);
        if(id.equals("no unit found")) {
            return null;
        }
        return unit;
    }

    @Override
    public City getCityAt(Position position) {
        String id = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_CITY_AT, String.class, position);
        City city = new CityProxy(requestor, id);
        if(id.equals("no city found")){
            return null;
        }
        return city;
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
        return requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_GET_AGE, Integer.class);
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean legal = requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_MOVE_UNIT, boolean.class, from, to);
        updateWorldChange(from);
        updateWorldChange(to);
        return legal;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(Game_OBJECTID, OperationNames.GAME_END_OF_TURN, null);
        updateTurnEnds();
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
        updateWorldChange(p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setTileFocus(Position position) {
        updateTileFocus(position);
    }

    public void updateWorldChange(Position position) {
        observer.worldChangedAt(position);
    }

    public void updateTurnEnds() {
        observer.turnEnds(getPlayerInTurn(), getAge());
    }

    public void updateTileFocus(Position position) {
        observer.tileFocusChangedAt(position);
    }

}
