package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.stub.StubUnit;

public class HotCivUnitInvoker implements Invoker{
        private Gson gson;

    public HotCivUnitInvoker() {
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

        Unit unit = lookUpUnit(objectId);

        return null;
    }

    private Unit lookUpUnit(String objectId) {
        Unit unit = new StubUnit("archer", Player.GREEN);
        return unit;
    }
}
