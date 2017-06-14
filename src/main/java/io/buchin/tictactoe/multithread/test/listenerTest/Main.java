package io.buchin.tictactoe.multithread.test.listenerTest;

/**
 * Created by IBuchin on 14.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        B c = new B();

        a.addListener(b);
        a.addListener(c);

        a.doSomething();
    }
}
