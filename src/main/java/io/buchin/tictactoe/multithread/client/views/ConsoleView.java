package io.buchin.tictactoe.multithread.client.views;

import com.sun.org.apache.xpath.internal.SourceTree;
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

    public ConsoleView(){}

    public void initGame() {
        getUserName();

    }

    private void getUserName() {
        printMessage("Enter your UserName to start the game.");
        printMessage("Username can not be empty!");
        try {
            while (true) {
                String userName = reader.readLine();
                if (userName.isEmpty()) printMessage("You entered an empty UserName.");
                else {
                    printMessage("The search for an opponent has begun");
                    printMessage("After the finish, the opponent's name will be displayed and the game will start.");
                    controller.userSetUserName(userName);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void printMessage(String message) {
        System.out.println(message);
    }

    public void setController(ViewListener controller) {
        this.controller = controller;
    }
}
