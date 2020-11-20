package hotciv.variants.implementations.broker;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;
import hotciv.framework.Player;

public class HotCivGameInvoker implements Invoker {
    private Game servant;
    private final Gson gson;

    public HotCivGameInvoker(Game servant) {
        this.servant = servant;
        this.gson = new Gson();
    }

    @Override
    public String handleRequest(String request) {
        ReplyObject reply = new ReplyObject(200, gson.toJson(Player.RED.toString()));
        //JSON PARSE
        return gson.toJson(reply);
    }
}
