package hotciv.variants.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.WorldLayoutStrategy;

import java.util.Map;


public class DeltaCivWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public void createWorld(GameImpl game) {
        String[] layout =
                new String[]{
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        Map<Position, Tile> newTileMap = Utility.worldGenerator(layout);
        ((GameImpl) game).setWorldMap(newTileMap);

        Position redCityPos = new Position(8, 12);
        Position blueCityPos = new Position(4, 5);
        CityImpl redCity = new CityImpl(Player.RED);
        CityImpl blueCity = new CityImpl(Player.BLUE);
        game.addCity(redCityPos, redCity);
        game.addCity(blueCityPos, blueCity);


    }
}
