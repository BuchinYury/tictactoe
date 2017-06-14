package io.buchin.tictactoe.multithread.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class ServerMain {
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(3345)) {

            System.out.println("Server socket created, command console reader for listen to server commands");

            while (!server.isClosed()) {
                Socket client = server.accept();
                executor.execute(new ClientThread(client));
                System.out.println("Connection accepted.");
            }

            executor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
