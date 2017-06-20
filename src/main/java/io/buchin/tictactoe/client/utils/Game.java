package io.buchin.tictactoe.client.utils;

import io.buchin.tictactoe.client.controllers.GameController;
import io.buchin.tictactoe.client.models.GameModel;
import io.buchin.tictactoe.client.views.ConsoleView;

/**
 * Created by IBuchin on 15.06.2017.
 */
public class Game {
    public static void startGame(String[] args){
        if (args.length == 0){
            args = new String[]{"localhost", Integer.toString(3345)};
        } else {

        }

        ConsoleView consoleView = new ConsoleView();
        GameModel gameModel = new GameModel(consoleView);
        GameController gameController = new GameController(gameModel, args);
        consoleView.setController(gameController);
        consoleView.initGame();
    }
}
