package hotciv.variants.implementations;

import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.GameImpl;
import hotciv.utility.AdapterFractalMap;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.WorldLayoutStrategy;

import java.util.Map;

public class RandomWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public void createWorld(GameImpl game) {
        String[] layout = AdapterFractalMap.fractalMap();

        Map<Position, Tile> newTileMap = Utility.worldGenerator(layout);
        ((GameImpl)game).setWorldMap(newTileMap);

    }
}
