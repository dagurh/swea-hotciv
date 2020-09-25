package hotciv.variants;

import hotciv.framework.Position;
import hotciv.framework.Tile;

import java.util.Map;

public interface WorldLayoutStrategy {
    void createWorld(Map<Position, Tile> tileMap);
}
