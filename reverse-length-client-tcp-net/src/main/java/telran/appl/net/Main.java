package telran.appl.net;

import java.io.IOException;

import telran.net.TcpClient;
import telran.view.*;

public class Main {
    static TcpClient tcpClient;

    public static void main(String[] args) {
        Item[] items = {
            Item.of("Start session", Main::startSession),
            Item.of("Exit", Main::exit, true)
        };
        Menu menu = new Menu("Reverse-Length Application", items);
        menu.perform(new StandartInputOutput());
    }

    static void startSession(InputOutput io) {
        String host = io.readString("Enter hostname");
        int port = io.readNumberRange("Enter port", "Wrong port", 3000, 50000).intValue();
        exit(io);
        tcpClient = new TcpClient(host, port);
        Item[] items = {
            Item.of("Get reverse string", Main::getReverseString),
            Item.of("Get length of string", Main::getLengthOfString),
            Item.ofExit()
        };
        Menu menu = new Menu("String operations", items);
        menu.perform(io);
    }

    static void getReverseString(InputOutput io) {
        String string = io.readString("Enter any string");
        String response = tcpClient.sendAndReceive("reverse", string);
        io.writeLine(response);
    }

    static void getLengthOfString(InputOutput io) {
        String string = io.readString("Enter any string");
        String response = tcpClient.sendAndReceive("length", string);
        io.writeLine(response);
    }

    static void exit(InputOutput io) {
        if (tcpClient != null) {
            try {
                tcpClient.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}