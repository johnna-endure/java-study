package week10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedTest {

    @Test
    public void putTest() throws InterruptedException {
        SyncMock m = new SyncMock();

        Thread t1 = new Thread(() -> {
            synchronized (this) {
                m.method("대기");
            }
        });

        t1.start();

        Thread.sleep(1000);
        synchronized (this) {
            m.hello();
        }
    }
}

class SyncMock {
    public String message = "hello";

    public void method(String action) {
        if (action.equals("대기")) {
            while (true) {
            }
        }
    }

    public void hello() {
        System.out.println("Hello");
    }
}