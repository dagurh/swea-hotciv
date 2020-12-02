package hotciv.variants.implementations.broker;

import frds.broker.Requestor;
import hotciv.framework.Tile;

import java.util.UUID;

public class TileProxy implements Tile {

    private final Requestor requestor;
    private final String Tile_OBJECTID;

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
        Tile_OBJECTID = UUID.randomUUID().toString();
    }

    public String getTile_OBJECTID() {
        return Tile_OBJECTID;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(Tile_OBJECTID, OperationNames.TILE_GET_TYPE_STRING, String.class);
    }
}
