package io.buchin.tictactoe.client.link.listeners;

/**
 * Created by IBuchin on 14.06.2017.
 */
public interface ViewListener {
    void userSetUserName(String userName);

    void userMove(int userMove);

    void userWaitEnemyMove();

    void playAgain(int n);
}
