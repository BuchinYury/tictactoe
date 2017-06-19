package io.buchin.tictactoe.multithread.client.controllers;

import io.buchin.tictactoe.multithread.client.link.listeners.ControllerListener;
import io.buchin.tictactoe.multithread.client.link.listeners.ViewListener;

import java.io.*;
import java.net.Socket;

/**
 * Created by IBuchin on 13.06.2017.
 */
@SuppressWarnings("uncheked")
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
        //TODO отправка имени пользователя на сервер и ожидание возвращения id партии, имени, роли врага, роли пользователя от сервера
//        String enemyName = "Enemy";
//        int roleUser = 1;
//        int roleEnemy = 2;
        String[] serverResponse = sendToServerUserName(userName);
        //TODO

        int gameID = Integer.parseInt(serverResponse[0]);
        String enemyName = serverResponse[1];
        int roleUser = Integer.parseInt(serverResponse[2]);
        int roleEnemy = Integer.parseInt(serverResponse[3]);


        model.firstChangeState(userName, enemyName, roleUser, roleEnemy);
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

            //TODO протокол обшения с сервером через JSON
            oos.writeUTF(userName);
            oos.flush();

            String in = ois.readUTF();
            //TODO

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
