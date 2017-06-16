package io.buchin.tictactoe.multithread.client.link.listeners;

/**
 * Created by IBuchin on 14.06.2017.
 */
public interface ModelListener {
    void firstChangeState(String enemyName, int roleUser, int[] board);

    void enemyMove(int[] board);

    void gameOver(String winner);

    void drawGame();

    void initPlayAgain(String username);
}
