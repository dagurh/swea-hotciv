package hotciv.variants.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.variants.interfaces.WorldLayoutStrategy;

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
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'd' ) { type = GameConstants.DESERT; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                game.addTile(p, new TileImpl(type));
                Position redCityPos = new Position(8,12);
                Position blueCityPos = new Position(4,5);
                CityImpl redCity = new CityImpl(Player.RED);
                CityImpl blueCity = new CityImpl(Player.BLUE);
                game.addCity(redCityPos, redCity);
                game.addCity(blueCityPos, blueCity);
            }
        }
    }
}
