package io.buchin.tictactoe.multithread.server;

import io.buchin.tictactoe.multithread.server.models.Party;
import io.buchin.tictactoe.multithread.server.models.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IBuchin on 19.06.2017.
 */
public class ServerMain {
    public static void main(String[] args) {
        List<Player> waitToPlay = new CopyOnWriteArrayList<>();

        try (ServerSocket server = new ServerSocket(3345)) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                Player player = new Player(client);
                if (waitToPlay.isEmpty()) {
                    waitToPlay.add(player);
                } else {
                    if (new Random().nextBoolean()) {
                        player.setRole("X");

                        Player enemy = waitToPlay.get(0);
                        enemy.setRole("O");

                        new Party(player, enemy);
                    } else {
                        Player enemy = waitToPlay.get(0);
                        enemy.setRole("X");

                        player.setRole("O");

                        new Party(enemy, player);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
