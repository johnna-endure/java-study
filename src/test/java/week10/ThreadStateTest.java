package week10;

import org.junit.jupiter.api.Test;

public class ThreadStateTest {

    @Test
    public void runnableTest() {
        Thread t = new Thread(() -> {
            System.out.println("started.");
        });
        t.start();
        System.out.println(t.getState()); // RUNNABLE
    }

    @Test
    public void blockedTest() throws InterruptedException {
        Thread a = new Thread(ThreadStateTest::resource);
        Thread b = new Thread(ThreadStateTest::resource);

        a.start();
        Thread.sleep(1000);
        b.start();

        System.out.println("a is " + a.getState());
        System.out.println("b is " + b.getState());
        System.exit(0);
    }

    public static synchronized void resource() {
        while (true) {
        }
    }

}
