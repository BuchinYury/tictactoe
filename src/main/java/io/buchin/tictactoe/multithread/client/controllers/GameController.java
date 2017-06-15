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
//        model.setUserName(userName);
        //TODO отправка имени пользователя на сервер и ожидание возвращения имени врага от сервера
        String enemyName = "Enemy";


    }
}
