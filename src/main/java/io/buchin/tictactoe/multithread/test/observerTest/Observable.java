package io.buchin.tictactoe.multithread.test.observerTest;

/**
 * Created by IBuchin on 14.06.2017.
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
