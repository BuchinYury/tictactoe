package io.buchin.tictactoe.multithread.server.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by IBuchin on 19.06.2017.
 */
public class Player implements Runnable {
    private String playerName;
    private String playerRole;
    private Socket playerSocket;
    private int move = -1;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(playerSocket.getOutputStream());
            DataInputStream in = new DataInputStream(playerSocket.getInputStream());


            while (!playerSocket.isClosed()) {
                String entry = in.readUTF();

                if (entry.equalsIgnoreCase("quit")) {
                    out.writeUTF("Server reply - " + entry + " - OK");
                    Thread.sleep(3000);
                    break;
                }

                out.writeUTF("Server reply - " + entry + " - OK");

                out.flush();
            }
            in.close();
            out.close();

            playerSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRole(String playerRole) {
        this.playerRole = playerRole;
    }
}
