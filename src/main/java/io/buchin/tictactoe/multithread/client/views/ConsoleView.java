package io.buchin.tictactoe.multithread.client.views;

import io.buchin.tictactoe.multithread.client.link.listeners.ModelListener;
import io.buchin.tictactoe.multithread.client.link.listeners.ViewListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class ConsoleView implements ModelListener {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ViewListener controller;

    public ConsoleView() {
    }

    //Метод запуска циклов игры
    public void initGame() {
        getUserName();
    }

    //Методы интерфейса ModelListener
    @Override
    public void firstChangeState(String enemyName, int roleUser, int[] board) {
        separateMessage();
        printMessage("Your opponent's name is - " + enemyName + ".");
        switch (roleUser) {
            case 1:
                printMessage("You're playing - X.");
                printBoard(board);
                printMessage("Select the cell number where you want to make a move.");
                int userMove = getNumber(board);
                controller.userMove(userMove);
                break;
            case 2:
                printMessage("You're playing - O.");
                printBoard(board);
                printMessage("When it's your turn to walk, select an unoccupied cell number.");
                printMessage("Waiting for the enemies move.");
                controller.userWaitEnemyMove();
                break;
        }

    }

    @Override
    public void enemyMove(int[] board) {
        separateMessage();
        printBoard(board);
        printMessage("Select the cell number where you want to make a move.");
        int userMove = getNumber(board);
        controller.userMove(userMove);
    }

    @Override
    public void gameOver(String winner) {
        separateMessage();
        printMessage("The winner of this game - " + winner);
        playAgain();
    }

    @Override
    public void drawGame() {
        separateMessage();
        printMessage("The game is played in draw");
        playAgain();
    }

    @Override
    public void initPlayAgain(String username) {
        searchEnemy(username);
    }


    //Вспомогательные методы
    private void getUserName() {
        printMessage("Enter your UserName to start the game.");
        printMessage("Username can not be empty!");
        try {
            while (true) {
                String userName = reader.readLine();
                if (userName.isEmpty()) printMessage("You entered an empty UserName.");
                else {
                    searchEnemy(userName);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Методы работы с доской
    private void printBoard(int[] board) {
        System.out.println("     |     |     ");
        for (int i = 0; i < board.length; i++) {
            if (i != 0) {
                if (i % 3 == 0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                } else
                    System.out.print("|");
            }

            if (board[i] == 0) System.out.print("  " + i + "  ");
            if (board[i] == 1) System.out.print("  X  ");
            if (board[i] == 2) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");
    }


    private int getNumber(int[] board) {
        while (true) {
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < board.length && board[n] == 0) {
                    return n;
                }
                System.out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            } catch (IOException e) {
                System.out.println("Alarma");
            }
        }
    }

    private void searchEnemy(String userName){
        printMessage("The search for an opponent has begun.");
        printMessage("After the finish, the opponent's name will be displayed and the game will start.");
        controller.userSetUserName(userName);
    }

    private void playAgain(){
        printMessage("Would you like to play again?");
        while (true) {
            printMessage("Please enter a number:");
            printMessage("1 - Search for a new game");
            printMessage("2 - Exit the game");
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n == 1 || n == 2) controller.playAgain(n);
                else {
                    printMessage("You entered an unknown command");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            } catch (IOException e) {
            }
        }
    }

    // Методы вывода сообщений
    private void printMessage(String message) {
        System.out.println(message);
    }

    private void separateMessage() {
        System.out.println();
        System.out.println("*******************************************************************************");
    }


    // Метод для установки ViewListener
    public void setController(ViewListener controller) {
        this.controller = controller;
    }

}
