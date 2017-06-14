package io.buchin.tictactoe.multithread.client.models;

/**
 * Created by IBuchin on 13.06.2017.
 */
public class User {
    private String userName;
    private String enemyName;

    //   1 - X, 0 - O
    private int role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role){
        switch (role){
            case 0: this.role = 0; break;
            case 1: this.role = 1; break;
        }
    }
}
