package hotciv.variants.implementations;

import hotciv.framework.*;

public class LogDecorator implements Game {

    private Game game;

    public LogDecorator (Game g){
        game = g;
    }

    @Override
    public String getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        System.out.println("It is " + game.getPlayerInTurn() + "'s turn.");
        return game.getPlayerInTurn();
    }

    @Override
    public Object getWinner() {
        System.out.println("The winner is " + game.getWinner() + ".");
        return game.getWinner();
    }

    @Override
    public int getAge() {
        System.out.println("The current age is " + game.getAge() + ".");
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println(game.getPlayerInTurn() + " moves " + game.getUnitAt(from).getTypeString() + " from " + from + " to " + to + ".");
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn() + " ends turn.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.println(game.getPlayerInTurn() + " changes work force focus in city at " + p + " to " + balance + " focus.");
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(game.getPlayerInTurn() + " changes production in  a city at " + p + " to " + unitType + ".");
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println(game.getPlayerInTurn() + "'s " + game.getUnitAt(p).getTypeString() + " performs an action.");
        game.performUnitActionAt(p);
    }
}
