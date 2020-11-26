package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.stub.StubCity;

public class HotCivCityInvoker implements Invoker {
    private Gson gson;

    public HotCivCityInvoker() {
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

        City city = lookUpCity(objectId);

        return null;
    }

    private City lookUpCity(String objectId) {
        City city = new StubCity(Player.GREEN);
        return city;
    }
}
