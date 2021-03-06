package io.buchin.tictactoe.client.link.listeners;

/**
 * Created by IBuchin on 14.06.2017.
 */
public interface ControllerListener {
    void firstChangeState(int gameID, String userName, String enemyName, int roleUser, int roleEnemy);

    void changeState(int userMove, int enemyMove);
    void changeState(int enemyMove);

    void playAgain();
}
