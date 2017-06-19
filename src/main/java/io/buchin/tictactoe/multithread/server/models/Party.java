package io.buchin.tictactoe.multithread.server.models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IBuchin on 16.06.2017.
 */
public class Party {
    private int gameID;

    private Player playerX;
    private Player playerO;

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public Party(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
    }
}
