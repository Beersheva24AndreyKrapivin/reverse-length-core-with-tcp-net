package telran.appl.net;

import telran.net.TcpServer;

public class Main {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        ReverseLengthProtocol reverseLengthProtocol = new ReverseLengthProtocol();
        TcpServer tcpServer = new TcpServer(reverseLengthProtocol, PORT);
        tcpServer.run();
    }
}