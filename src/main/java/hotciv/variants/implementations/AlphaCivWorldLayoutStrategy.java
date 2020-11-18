package hotciv.variants.implementations;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.interfaces.WorldLayoutStrategy;


public class AlphaCivWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public void createWorld(GameImpl game){
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
        for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
            game.addTile(new Position(i, j), new TileImpl(GameConstants.PLAINS));
        }
        Position mountainsPos = new Position(2, 2);
        TileImpl mountainsTile = new TileImpl(GameConstants.MOUNTAINS);
        game.addTile(mountainsPos, mountainsTile);

        Position hillsPos = new Position(0, 1);
        TileImpl hillsTile = new TileImpl(GameConstants.HILLS);
        game.addTile(hillsPos, hillsTile);

        Position oceanPos = new Position(1, 0);
        TileImpl oceanTile = new TileImpl(GameConstants.OCEANS);
        game.addTile(oceanPos, oceanTile);
    }
        Position redCityPos = new Position(1, 1);
        Position blueCityPos = new Position(4, 1);
        CityImpl redCity = new CityImpl(Player.RED);
        CityImpl blueCity = new CityImpl(Player.BLUE);
        game.addCity(redCityPos, redCity);
        game.addCity(blueCityPos, blueCity);

        Position redArcherPos = new Position(2, 0);
        Position blueLegionPos = new Position(3, 2);
        Position redSettlerPos = new Position(4, 3);
        UnitImpl redArcher = new UnitImpl("archer", Player.RED);
        UnitImpl blueLegion = new UnitImpl("legion", Player.BLUE);
        UnitImpl redSettler = new UnitImpl("settler", Player.RED);
        game.addUnit(redArcherPos, redArcher);
        game.addUnit(blueLegionPos, blueLegion);
        game.addUnit(redSettlerPos, redSettler);
    }
}
