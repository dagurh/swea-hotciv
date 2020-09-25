package hotciv.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.TileImpl;

import java.util.Map;

public class AlphaWorldLayout implements WorldLayoutStrategy {
    @Override
    public void createWorld(Map<Position, Tile> tileMap){
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
        for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
            tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
        }
        Position mountainsPos = new Position(2, 2);
        TileImpl mountainsTile = new TileImpl(GameConstants.MOUNTAINS);
        tileMap.put(mountainsPos, mountainsTile);

        Position hillsPos = new Position(0, 1);
        TileImpl hillsTile = new TileImpl(GameConstants.HILLS);
        tileMap.put(hillsPos, hillsTile);

        Position oceanPos = new Position(1, 0);
        TileImpl oceanTile = new TileImpl(GameConstants.OCEANS);
        tileMap.put(oceanPos, oceanTile);
    }
    }
}
