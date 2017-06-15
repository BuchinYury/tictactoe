package io.buchin.tictactoe.multithread.client.models;

import io.buchin.tictactoe.multithread.client.link.listeners.ControllerListener;
import io.buchin.tictactoe.multithread.client.link.listeners.ModelListener;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class GameModel implements ControllerListener{
    private String userName;
    private String userRole;

    private String enemyName;
    private String enemyRole;

    private String nowWalking;

    private int[] board = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};

    private ModelListener view;

    public void setView(ModelListener view) {
        this.view = view;
    }

    public void initGame(){
        view.initViewEvent(board);
    }
}
