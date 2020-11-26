package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Tile;
import hotciv.stub.StubTile;

public class HotCivTileInvoker implements Invoker {

    private Gson gson;

    public HotCivTileInvoker() {
        gson = new Gson();
    }


    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String objectId = requestObject.getObjectId();
        String operationName = requestObject.getOperationName();
        String payload = requestObject.getPayload();

        ReplyObject replyObject = null;

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();

        Tile tile = lookUpTile(objectId);

        return null;
    }

    private Tile lookUpTile(String objectId) {
        Tile tile = new StubTile("plains");
        return tile;
    }
}
