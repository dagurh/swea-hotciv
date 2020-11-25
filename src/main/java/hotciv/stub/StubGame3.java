package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.*;

public class StubGame3 implements Game, Servant {

    Position position_of_green_city = new Position(1,1);

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
        if(p.equals(position_of_green_city)){
        return new StubCity(Player.GREEN);
    }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return Player.GREEN;
    }

    @Override
    public Object getWinner() {
        return Player.YELLOW;
    }

    @Override
    public int getAge() {
        return 42;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return true;
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
