package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.Tile;

public class TileProxy implements Tile {

    private final Requestor requestor;

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return null;
    }
}
