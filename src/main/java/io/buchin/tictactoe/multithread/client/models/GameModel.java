package io.buchin.tictactoe.multithread.client.models;

import io.buchin.tictactoe.multithread.client.link.listeners.ControllerListener;
import io.buchin.tictactoe.multithread.client.link.listeners.ModelListener;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class GameModel implements ControllerListener {
    private int gameID;

    private String userName;
    private int userRole;

    private String enemyName;
    private int enemyRole;

    private int[] board = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};

    private ModelListener view;

    public GameModel(ModelListener view) {
        this.view = view;
    }

    @Override
    public void firstChangeState(String userName, String enemyName, int roleUser, int roleEnemy) {
        this.userName = userName;
        this.enemyName = enemyName;
        userRole = roleUser;
        enemyRole = roleEnemy;

        view.firstChangeState(enemyName, roleUser, board);
    }

    @Override
    public void changeState(int userMove, int enemyMove) {
        board[userMove] = userRole;
        if (isGameOver(userMove)) view.gameOver(userName);

        enemyMove(enemyMove);
    }

    @Override
    public void changeState(int enemyMove) {
        enemyMove(enemyMove);
    }

    @Override
    public void playAgain() {
        board = new int[]{0, 0, 0,
                0, 0, 0,
                0, 0, 0};
        view.initPlayAgain(userName);
    }

    //Вспомогательные методы
    private void enemyMove(int enemyMove) {
        board[enemyMove] = enemyRole;
        if (isGameOver(enemyMove)) view.gameOver(enemyName);

        if (isDraw()) view.drawGame();

        view.enemyMove(board);
    }

    private boolean isGameOver(int n) {
        //поиск совпадений по горизонтали
        int row = n - n % 3; //номер строки - проверяем только её
        if (board[row] == board[row + 1] &&
                board[row] == board[row + 2]) return true;

        //поиск совпадений по вертикали
        int column = n % 3; //номер столбца - проверяем только его
        if (board[column] == board[column + 3])
            if (board[column] == board[column + 6]) return true;

        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n % 2 != 0) return false;

        //проверяем принадлежит ли к левой диагонали значение
        if (n % 4 == 0) {
            //проверяем есть ли совпадения на левой диагонали
            if (board[0] == board[4] &&
                    board[0] == board[8]) return true;
            if (n != 4) return false;
        }

        return board[2] == board[4] &&
                board[2] == board[6];
    }

    private boolean isDraw() {
        for (int n : board) if (n == 0) return false;
        return true;
    }

}
