package hotciv.variants.implementations.broker;

import java.util.*;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import javax.servlet.http.HttpServletResponse;

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


            if (requestObject.getOperationName().equals(OperationNames.GET_WINNER)) {
                reply = new ReplyObject(200, gson.toJson(servant.getWinner().toString()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GET_AGE)) {
                reply = new ReplyObject(200, gson.toJson(servant.getAge()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.GET_PLAYERINTURN)) {
                reply = new ReplyObject(200, gson.toJson(servant.getPlayerInTurn()));
            }
            else if (requestObject.getOperationName().equals(OperationNames.MOVE_UNIT)){
                Position from = gson.fromJson(array.get(0), Position.class);
                Position to = gson.fromJson(array.get(1), Position.class);
                reply = new ReplyObject(200, gson.toJson(servant.moveUnit(from, to)));
            }
            else if (requestObject.getOperationName().equals(OperationNames.END_OF_TURN)){
                servant.endOfTurn();
                reply = new ReplyObject(200, "end of turn called");
            }
            else if (requestObject.getOperationName().equals(OperationNames.CHANGEWORKFORCEFOCUS)){
                Position p = gson.fromJson(array.get(0), Position.class);
                String balance = gson.fromJson(array.get(1), String.class);
                servant.changeWorkForceFocusInCityAt(p,balance);
                reply = new ReplyObject(200, "changed workForceFocus to " + balance + "in city with position " + p);
            }




        // And marshall the reply
        return gson.toJson(reply);
    }

}

