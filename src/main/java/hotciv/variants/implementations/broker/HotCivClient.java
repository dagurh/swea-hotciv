package hotciv.variants.implementations.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;


public class HotCivClient {


    public HotCivClient(String hostname) {
        System.out.println("== HotCiv Story Test Client (Socket) (host " + hostname + ") ===");

        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(hostname, 37329);

        Requestor requestor = new StandardJSONRequestor(crh);

        Game game = new GameProxy(requestor);
        game.addObserver(new NullObserver());

        testSimpleMethods(game);

        crh.close();
    }

    public static void main(String[] args) {
        new HotCivClient(args[0]);
    }

    private void testSimpleMethods(Game game) {
        System.out.println("Testing simple methods ===");
        System.out.println(" - Game age:" + game.getAge());
        System.out.println(" - Game winner:" + game.getWinner());
        System.out.println(" - Game PlayerInTurn" + game.getPlayerInTurn());
        System.out.println(" - Game move (1,0) -> (2,1): " + game.moveUnit(new Position(1, 0), new Position(2, 1)));

        game.endOfTurn();
        System.out.println(" - PlayerInTurn after 1st turn: " + game.getPlayerInTurn());
    }

}



