package hotciv.variants.implementations.broker;


import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.stub.StubGame2;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class HotCivServer {
    public static void main(String[] args) throws Exception {
        new ServerMainSocket();
    }

    private static class ServerMainSocket {
        public ServerMainSocket() {
            int port = 37329;

            Game servant = new StubGame2();

            servant.addObserver(new NullObserver());

            Invoker invoker = new HotCivGameInvoker(servant);

            ServerRequestHandler ssrh = new SocketServerRequestHandler();
            ssrh.setPortAndInvoker(port, invoker);

            try {
                System.out.println("=== HotCiv Socket based Server request handler running on IP: " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("=== HotCiv SOCKET based Server Request Handler (port: " + port + ") ===");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            ssrh.start();
        }
    }
}
