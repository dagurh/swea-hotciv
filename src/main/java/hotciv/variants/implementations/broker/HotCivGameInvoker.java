package hotciv.variants.implementations.broker;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;


import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class HotCivGameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;
    private final NameService nameService;
    private ReplyObject reply;

    public HotCivGameInvoker(Game servant, NameService nameService) {
        this.servant = servant;
        this.gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        JsonArray array = new JsonParser().parse(requestObject.getPayload()).getAsJsonArray();

                if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)) {
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(servant.getWinner().toString()));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_AGE)) {
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(servant.getAge()));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAY_IN_TURN)) {
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(servant.getPlayerInTurn()));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_MOVE_UNIT)) {
                    Position from = gson.fromJson(array.get(0), Position.class);
                    Position to = gson.fromJson(array.get(1), Position.class);
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(servant.moveUnit(from, to)));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)) {
                    servant.endOfTurn();
                    String endOfTurnCalled = "end of turn called";
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, endOfTurnCalled);
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_CHANGE_WORK_FORCE_FOCUS)) {
                    Position p = gson.fromJson(array.get(0), Position.class);
                    String balance = gson.fromJson(array.get(1), String.class);
                    servant.changeWorkForceFocusInCityAt(p, balance);
                    String balanceChanged = balance + " is now the workforce focus in city at " + p;
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(balanceChanged));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_CHANGE_PRODUCTION)) {
                    Position p = gson.fromJson(array.get(0), Position.class);
                    String production = gson.fromJson(array.get(1), String.class);
                    servant.changeProductionInCityAt(p, production);
                    String productionChanged = production + " is now the production in city at " + p;
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(productionChanged));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_UNIT_ACTION)) {
                    Position p = gson.fromJson(array.get(0), Position.class);
                    servant.performUnitActionAt(p);
                    String unitPerformedActionAt = "Unit performed action at position " + p;
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(unitPerformedActionAt));
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_CITY_AT)) {
                    Position position = gson.fromJson(array.get(0), Position.class);
                    City city = servant.getCityAt(position);
                    if (city != null) {
                        String id = city.getObjectID();
                        nameService.putCity(id, city);
                        reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(id));
                    } else {
                        String noCityFound = "no city found";
                        reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(noCityFound));
                    }
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_UNIT_AT)) {
                    Position p = gson.fromJson(array.get(0), Position.class);
                    Unit unit = servant.getUnitAt(p);
                    if (unit != null) {
                        String id = unit.getObjectID();
                        nameService.putUnit(id, servant.getUnitAt(p));
                        reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(id));
                    } else {
                        String noUnitFound = "no unit found";
                        reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(noUnitFound));
                    }
                } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TILE_AT)) {
                    Position p = gson.fromJson(array.get(0), Position.class);
                    reply = new ReplyObject(HttpServletResponse.SC_ACCEPTED, gson.toJson(servant.getTileAt(p)));
                }

        // And marshall the reply
        return gson.toJson(reply);
    }

}

