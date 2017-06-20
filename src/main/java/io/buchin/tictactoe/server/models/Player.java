package io.buchin.tictactoe.server.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

/**
 * Created by IBuchin on 19.06.2017.
 */
public class Player implements Runnable {
    private String playerName;
    private String playerRole;
    private Socket playerSocket;
    private Party party;
    private int move = -1;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    public void run() {
        System.out.println(playerName + " - " + playerRole);
        try {
            DataOutputStream out = new DataOutputStream(playerSocket.getOutputStream());
            DataInputStream in = new DataInputStream(playerSocket.getInputStream());

            while (!playerSocket.isClosed()) {
                // Получаем сообщение от игрока
                if (in.available() != 0) {
                    String message = in.readUTF();
                    System.out.println(message);
                    // Парсим его и возвращаем JSON ответа
                    String response = serverReaction(message);

                    // Отправляем JSON с ответом пользователю
                    out.writeUTF(response);
                    out.flush();
                }
            }

            in.close();
            out.close();

            playerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serverReaction(String message) {
        JsonElement jelement = new JsonParser().parse(message);
        Set<String> keyElem = jelement.getAsJsonObject().keySet();

        String result = "";

        if (keyElem.contains("userName")) {

            result = initRespons(jelement.getAsJsonObject().get("userName").getAsString());
        }
        if (keyElem.contains("userMove")) {
            result = moveResponse(jelement.getAsJsonObject().get("userMove").getAsString());
        }

        return result;
    }

    private String initRespons(String userName) {
        playerName = userName;

        int gameID = party.getGameID();

        String[] enemyNameRole;

        if (playerRole.equals("X")) {
            enemyNameRole = enemyNameRole("O");
        } else {
            enemyNameRole = enemyNameRole("X");
        }

        String result = "{\n" +
                "  \"gameID\": \"" + gameID + "\",\n" +
                "  \"enemyName\": \"" + enemyNameRole[0] + "\",\n" +
                "  \"enemyRole\": \"" + enemyNameRole[1] + "\",\n" +
                "  \"userRole\": \"" + playerRole + "\"\n" +
                "}";

        System.out.println(result);

        return result;
    }

    private String[] enemyNameRole(String playerRole){
        String[] result = new String[2];

        while (true) {
            result[0] = party.getPlayerName(playerRole);
            if (result[0] != null) break;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result[1] = playerRole;

        return result;
    }

    private String moveResponse(String userMove) {
        return "";
    }



    public void setRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getPlayerName() {
        return playerName;
    }
}
