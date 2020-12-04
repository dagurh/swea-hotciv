package hotciv.variants.implementations.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;


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

        DrawingEditor editor =
                new MiniDrawApplication( "Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        editor.setTool(new CompositionTool(editor, game));
    }

}



