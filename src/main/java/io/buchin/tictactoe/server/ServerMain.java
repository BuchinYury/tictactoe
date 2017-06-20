package io.buchin.tictactoe.server;

import io.buchin.tictactoe.server.models.Party;
import io.buchin.tictactoe.server.models.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IBuchin on 19.06.2017.
 */
public class ServerMain {
    public static void main(String[] args) {
        List<Player> waitToPlay = new CopyOnWriteArrayList<>();
        List<Party> partyPlay = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(2);

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

                        Party party = new Party(player, enemy);
                        partyPlay.add(party);

                        party.setGameID(partyPlay.indexOf(party));

                    } else {
                        Player enemy = waitToPlay.get(0);
                        enemy.setRole("X");

                        player.setRole("O");

                        Party party = new Party(enemy, player);
                        partyPlay.add(party);

                        party.setGameID(partyPlay.indexOf(party));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
