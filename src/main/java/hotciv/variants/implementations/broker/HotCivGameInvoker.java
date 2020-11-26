package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class HotCivGameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;
    private ReplyObject reply;

    public HotCivGameInvoker(Game servant) {
        this.servant = servant;
        this.gson = new Gson();
    }

    @Override
    public String handleRequest(String request) {



        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        JsonArray array = new JsonParser().parse(requestObject.getPayload()).getAsJsonArray();


            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)) {
                reply = new ReplyObject(200, gson.toJson(servant.getWinner().toString()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_AGE)) {
                reply = new ReplyObject(200, gson.toJson(servant.getAge()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAY_IN_TURN)) {
                reply = new ReplyObject(200, gson.toJson(servant.getPlayerInTurn()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_MOVE_UNIT)){
                Position from = gson.fromJson(array.get(0), Position.class);
                Position to = gson.fromJson(array.get(1), Position.class);
                reply = new ReplyObject(200, gson.toJson(servant.moveUnit(from, to)));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)){
                servant.endOfTurn();
                reply = new ReplyObject(200, "end of turn called");
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_CHANGE_WORK_FORCE_FOCUS)){
                Position p = gson.fromJson(array.get(0), Position.class);
                String balance = gson.fromJson(array.get(1), String.class);
                servant.changeWorkForceFocusInCityAt(p,balance);
                reply = new ReplyObject(200, "changed workForceFocus to " + balance + " in city with position " + p);
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_CHANGE_PRODUCTION)) {
                Position p = gson.fromJson(array.get(0), Position.class);
                String production = gson.fromJson(array.get(1), String.class);
                servant.changeProductionInCityAt(p,production);
                reply = new ReplyObject(200, "changed production to " + production + " in city with position " + p);
            }
            else if (requestObject.getOperationName().equals(OperationNames.GAME_UNIT_ACTION)) {
                Position p = gson.fromJson(array.get(0), Position.class);
                servant.performUnitActionAt(p);
                reply = new ReplyObject(200, "Unit performed action at position" + p);
            }





        // And marshall the reply
        return gson.toJson(reply);
    }

}

