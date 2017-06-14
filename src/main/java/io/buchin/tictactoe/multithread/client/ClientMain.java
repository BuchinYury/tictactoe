package io.buchin.tictactoe.multithread.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {
                if (br.ready()) {
                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    String clientCommand = br.readLine();

                    oos.writeUTF(clientCommand);
                    oos.flush();

                    System.out.println("Clien sent message " + clientCommand + " to server.");
                    Thread.sleep(1000);

                    if (clientCommand.equals("quit")) {
                        System.out.println("Client kill connections");
                        Thread.sleep(2000);

                        if (ois.available() != 0) {
                            System.out.println("reading...");
                            String in = ois.readUTF();
                            System.out.println(in);
                        }
                        break;
                    }

                    System.out.println("Client sent message & start waiting for data from server...");
                    Thread.sleep(2000);

                    if (ois.available() != 0){
                        System.out.println("reading...");
                        String in = ois.readUTF();
                        System.out.println(in);
                    }
                }
            }

            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
