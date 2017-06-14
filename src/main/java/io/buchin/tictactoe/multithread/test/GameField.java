package io.buchin.tictactoe.multithread.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameField {

    static int[] canvas = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};

    //012, 345, 678, 036, 147, 258, 048, 246
    public static void main(String[] args) {

        boolean b;
        boolean isCurrentX = false;
        do {
            isCurrentX = !isCurrentX;
            drawCanvas();
            System.out.println("mark " + (isCurrentX ? "X" : "O"));
            int n = getNumber();
            canvas[n] = isCurrentX ? 1 : 2;
            b = !isGameOver(n);
            if (isDraw()) {
                System.out.println("Draw");
                return;
            }
        } while (b);
        drawCanvas();
        System.out.println();

        System.out.println("The winner is " + (isCurrentX ? "X" : "O") + "!");
    }

    static int getNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < canvas.length && canvas[n] == 0) {
                    return n;
                }
                System.out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            } catch (IOException e) {
            }
        }
    }

    static boolean isGameOver(int n) {
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n - n % 3; //номер строки - проверяем только её
        if (canvas[row] == canvas[row + 1] &&
                canvas[row] == canvas[row + 2]) return true;
        //поиск совпадений по вертикали
        int column = n % 3; //номер столбца - проверяем только его
        if (canvas[column] == canvas[column + 3])
            if (canvas[column] == canvas[column + 6]) return true;
        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n % 2 != 0) return false;
        //проверяем принадлежит ли к левой диагонали значение
        if (n % 4 == 0) {
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] &&
                    canvas[0] == canvas[8]) return true;
            if (n != 4) return false;
        }
        return canvas[2] == canvas[4] &&
                canvas[2] == canvas[6];
    }

    static void drawCanvas() {
        System.out.println("     |     |     ");
        for (int i = 0; i < canvas.length; i++) {
            if (i != 0) {
                if (i % 3 == 0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                } else
                    System.out.print("|");
            }

            if (canvas[i] == 0) System.out.print("  " + i + "  ");
            if (canvas[i] == 1) System.out.print("  X  ");
            if (canvas[i] == 2) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");
    }

    public static boolean isDraw() {
        for (int n : canvas) if (n == 0) return false;
        return true;
    }
}
