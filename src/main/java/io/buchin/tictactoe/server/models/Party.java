package io.buchin.tictactoe.server.models;

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

        playerX.setParty(this);
        playerO.setParty(this);

        executor.execute(playerX);
        executor.execute(playerO);
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public String getPlayerName(String role){
        if (role.equals("X")){
            return playerX.getPlayerName();
        } else {
            return playerO.getPlayerName();
        }
    }
}
