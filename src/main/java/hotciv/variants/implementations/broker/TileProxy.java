package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.Tile;

public class TileProxy implements Tile {

    private Requestor requestor;
    private final String tile_OBJECTID = "yoink";

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(tile_OBJECTID, OperationNames.TILE_GET_TYPE_STRING, String.class);
    }
}
