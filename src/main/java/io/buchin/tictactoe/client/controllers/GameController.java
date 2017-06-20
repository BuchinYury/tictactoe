package io.buchin.tictactoe.client.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.buchin.tictactoe.client.link.listeners.ControllerListener;
import io.buchin.tictactoe.client.link.listeners.ViewListener;

import java.io.*;
import java.net.Socket;
import java.util.Set;

/**
 * Created by IBuchin on 13.06.2017.
 */

public class GameController implements ViewListener {
    Socket socket;
    ControllerListener model;

    public GameController(ControllerListener model, String[] args) {
        this.model = model;
        try {
            socket = new Socket(args[0], Integer.parseInt(args[1]));
        } catch (IOException ignored) {}
    }


    @Override
    public void userSetUserName(String userName) {
        String[] serverResponse = sendToServerUserName(userName);

        int gameID = Integer.parseInt(serverResponse[0]);
        String enemyName = serverResponse[1];
        int roleUser = Integer.parseInt(serverResponse[2]);
        int roleEnemy = Integer.parseInt(serverResponse[3]);

        model.firstChangeState(gameID, userName, enemyName, roleUser, roleEnemy);
    }

    @Override
    public void userMove(int userMove) {
        //TODO отправка хода пользователя на сервер и ожидание ответа от сервера с ходом опонента
        int enemyMove = 3;
//        int enemyMove = sendToServerUserMove(userMove);
        //TODO

        model.changeState(userMove, enemyMove);

    }

    @Override
    public void userWaitEnemyMove() {
        //TODO запрос хода опонента
        int enemyMove = 3;
//        int enemyMove = waitResponseFromServerWithEnemyMove();
        //TODO
        model.changeState(enemyMove);
    }

    @Override
    public void playAgain(int n) {
        if (n == 1) {
//            sayServerWhatUserPlayAgain();
            model.playAgain();
        }
        if (n == 2) {
//            sayServerWhatUserGameEnd();
            try {
                socket.close();
            } catch (IOException ignored) {}
            System.exit(0);
        }
    }

    private String[] sendToServerUserName(String userName) {
        try (DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {

            // Формируем JSON для отправки на сервер
            String messageToServer = "{\n" +
                    "    \"userName\": \"" + userName + "\"\n" +
                    "}";

            // Отправляем сообщение на сервер
            oos.writeUTF(messageToServer);
            oos.flush();

            // Получаем ответ в формате JSON
            String in = ois.readUTF();

            System.out.println(in);

            // Парсим ответ и получаем масив с параметрами
            JsonElement jelement = new JsonParser().parse(in);
            Set<String> keyElem = jelement.getAsJsonObject().keySet();
            String[] result = new String[4];

            for (String key : keyElem){
                for (int i = 0; i < 4; i++){
                    String tmp = jelement.getAsJsonObject().get(key).getAsString();

                    if (tmp.equals("X")) tmp = "1";
                    if (tmp.equals("O")) tmp = "2";

                    result[i] = tmp;
                }
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
