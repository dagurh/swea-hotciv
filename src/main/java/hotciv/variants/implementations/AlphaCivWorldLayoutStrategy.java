package hotciv.variants.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
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
    }
}
