package io.buchin.tictactoe.multithread.client.utils;

import io.buchin.tictactoe.multithread.client.controllers.GameController;
import io.buchin.tictactoe.multithread.client.models.GameModel;
import io.buchin.tictactoe.multithread.client.views.ConsoleView;

/**
 * Created by IBuchin on 15.06.2017.
 */
public class Game {
    public static void startGame(){
        ConsoleView consoleView = new ConsoleView();
        GameModel gameModel = new GameModel(consoleView);
        GameController gameController = new GameController(gameModel);
        consoleView.setController(gameController);
        consoleView.initGame();
    }
}
