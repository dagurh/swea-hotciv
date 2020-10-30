package hotciv.variants.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.WorldLayoutStrategy;

import java.util.Map;

public class ThetaCivWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public void createWorld(GameImpl game) {
        String[] layout =
                new String[] {
                "...ooModdoo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...odd..........",
                ".ooodo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoddo.offoooo",
                "oodddodo...ooooo",
                ".ooMMMdooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
        };

        Map<Position, Tile> newTileMap = Utility.worldGenerator(layout);
        ((GameImpl)game).setWorldMap(newTileMap);

        Position redCityPos = new Position(8,12);
        Position blueCityPos = new Position(4,5);
        Position blueCaravanPos = new Position(9,6);
        Position redArcherPos = new Position(3,8);
        Position blueLegionPos = new Position(4,4);
        Position redSettlerPos = new Position(5,5);
        CityImpl redCity = new CityImpl(Player.RED);
        CityImpl blueCity = new CityImpl(Player.BLUE);
        game.addCity(redCityPos, redCity);
        game.addCity(blueCityPos, blueCity);
        game.addUnit(blueCaravanPos, new UnitImpl("caravan", Player.BLUE));
        game.addUnit(redArcherPos, new UnitImpl("archer", Player.RED));
        game.addUnit(blueLegionPos, new UnitImpl("legion", Player.BLUE));
        game.addUnit(redSettlerPos, new UnitImpl("settler", Player.RED));
        }

}
