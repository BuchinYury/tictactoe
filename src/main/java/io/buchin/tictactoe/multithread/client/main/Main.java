package io.buchin.tictactoe.multithread.client.main;

import io.buchin.tictactoe.multithread.client.models.GameModel;
import io.buchin.tictactoe.multithread.client.views.ConsoleView;

/**
 * Created by IBuchin on 14.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();

        GameModel gameModel = new GameModel();
        gameModel.setView(consoleView);
        gameModel.initGame();

    }
}
