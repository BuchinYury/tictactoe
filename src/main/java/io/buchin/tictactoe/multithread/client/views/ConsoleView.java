package io.buchin.tictactoe.multithread.client.views;

import io.buchin.tictactoe.multithread.client.link.listener.ModelListener;
import io.buchin.tictactoe.multithread.client.link.listener.ViewListener;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class ConsoleView implements ModelListener{
    private int[] board;

    private void printBoard(int[] board){
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

    @Override
    public void initEvent(int[] board) {
        printBoard(board);
    }
}
