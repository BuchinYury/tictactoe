package io.buchin.tictactoe.multithread.test.listenerTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBuchin on 14.06.2017.
 */
public class A {
    List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void doSomething() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).doEvent(Math.sin(i)); //class A не знает кто его слушает
        }
    }
}
