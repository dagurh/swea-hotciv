package hotciv.variants.implementations.broker;

import frds.broker.Invoker;
import frds.broker.RequestObject;
import com.google.gson.*;
import frds.broker.ReplyObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Unit;

import java.util.HashMap;


public class RootInvoker implements Invoker {

    private Gson gson;
    private Game servant;
    private ReplyObject reply;
    private HashMap<String, Invoker> invokerMap;

    public RootInvoker(Game servant) {
        gson = new Gson();
        this.servant = servant;
        invokerMap = new HashMap<>();
        NameService nameService = new HotCivNameService();
        invokerMap.put("game", new HotCivGameInvoker(servant, nameService));
        invokerMap.put("city", new HotCivCityInvoker(nameService));
        invokerMap.put("unit", new HotCivUnitInvoker(nameService));
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        String type = operationName.substring(0, operationName.indexOf(OperationNames.SEPARATOR));
        Invoker subInvoker = invokerMap.get(type);

        String reply;

        reply = subInvoker.handleRequest(request);

        return reply;
    }
}
