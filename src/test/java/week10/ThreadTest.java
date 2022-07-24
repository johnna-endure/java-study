package week10;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    public void testThread() {
        Thread a = new Thread() {
            @Override
            public void run() {
                System.out.println("a");
            }
        };
        a.start();

        System.out.println("Hello");
    }

    @Test
    public void runnableTest() {
        Thread a = new Thread(() -> {
            System.out.println("a");
        });

        a.start();
        System.out.println("Hello");
    }

    @Test
    public void newStateTest() {
        Thread thread = new Thread();
        System.out.println(thread.getState());
    }

    @Test
    public void testPriority() {
        Thread t = new Thread();
        t.setPriority(2);
    }
}

