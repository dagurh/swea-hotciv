package hotciv.variants.implementations.broker;


import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.stub.StubGame2;


public class HotCivServer {
    public static void main(String[] args) throws Exception {
        new ServerMainSocket();
    }

    private static class ServerMainSocket {
        public ServerMainSocket() {
            int port = 37321;

            Game servant = new StubGame2();

            servant.addObserver(new NullObserver());

            Invoker invoker = new HotCivGameInvoker(servant);

            ServerRequestHandler ssrh = new SocketServerRequestHandler();
            ssrh.setPortAndInvoker(port, invoker);

            System.out.println("=== HotCiv SOCKET based Server Request Handler (port: " + port + ") ===");

            ssrh.start();
        }
    }
}
