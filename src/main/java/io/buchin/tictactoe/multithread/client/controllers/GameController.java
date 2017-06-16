package io.buchin.tictactoe.multithread.client.controllers;

import io.buchin.tictactoe.multithread.client.link.listeners.ControllerListener;
import io.buchin.tictactoe.multithread.client.link.listeners.ViewListener;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class GameController implements ViewListener {
    ControllerListener model;

    public GameController(ControllerListener model) {
        this.model = model;
    }


    @Override
    public void userSetUserName(String userName) {
        //TODO отправка имени пользователя на сервер и ожидание возвращения имени и роли врага и роли пользователя от сервера
        String enemyName = "Enemy";
        int roleUser = 1;
        int roleEnemy = 2;
        //TODO

        model.firstChangeState(userName, enemyName, roleUser, roleEnemy);


    }

    @Override
    public void userMove(int userMove) {
        //TODO отправка хода пользователя на сервер и ожидание ответа от сервера с ходом опонента
        int enemyMove = 3;
        //TODO

        model.changeState(userMove, enemyMove);

    }

    @Override
    public void userWaitEnemyMove() {
        //TODO запррс хода опонента
        int enemyMove = 3;
        //TODO
        model.changeState(enemyMove);
    }

    @Override
    public void playAgain(int n) {
        if (n == 1) model.playAgain();
        if (n == 2) System.exit(0);
    }
}
