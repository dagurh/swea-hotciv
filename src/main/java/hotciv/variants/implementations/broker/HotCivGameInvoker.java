package hotciv.variants.implementations.broker;

import java.util.*;

import com.google.gson.*;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.framework.Player;

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


            if (requestObject.getOperationName().equals(OperationNames.GET_WINNER)) {
                reply = new ReplyObject(200, gson.toJson(Player.YELLOW.toString()));
            }


      

        // And marshall the reply
        return gson.toJson(reply);
    }

}

