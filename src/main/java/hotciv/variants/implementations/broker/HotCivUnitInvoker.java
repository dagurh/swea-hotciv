package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.stub.StubUnit;

public class HotCivUnitInvoker implements Invoker{
    private final NameService nameService;
    private Gson gson;
        private ReplyObject reply;

    public HotCivUnitInvoker(NameService nameService) {
        gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String objectId = requestObject.getObjectId();
        String operationName = requestObject.getOperationName();
        String payload = requestObject.getPayload();



        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();

        Unit unit = lookUpUnit(objectId);

        if (operationName.equals(OperationNames.UNIT_GET_TYPE_STRING)) {
            reply = new ReplyObject(200,gson.toJson(unit.getTypeString()));
        }
        else if (operationName.equals(OperationNames.UNIT_GET_OWNER)) {
            reply = new ReplyObject(200, gson.toJson(unit.getOwner()));
        }
        else if (operationName.equals(OperationNames.UNIT_GET_MOVE_COUNT)) {
            reply = new ReplyObject(200,gson.toJson(unit.getMoveCount()));
        }
        else if (operationName.equals(OperationNames.UNIT_GET_DEFENSIVE_STRENGTH)) {
            reply = new ReplyObject(200, gson.toJson(unit.getDefensiveStrength()));
        }
        else if (operationName.equals(OperationNames.UNIT_GET_ATTACKING_STRENGTH)) {
            reply = new ReplyObject(200, gson.toJson(unit.getAttackingStrength()));
        }
        return gson.toJson(reply);
    }

    private Unit lookUpUnit(String objectId) {
        return nameService.getUnit(objectId);
    }
}
