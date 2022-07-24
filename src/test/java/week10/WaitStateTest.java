package week10;

import org.junit.jupiter.api.Test;

public class WaitStateTest {

    @Test
    public void waitTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                staticResource("대기");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
//                throw new RuntimeException(e);
            }
        });

        t1.start();

        Thread.sleep(1000);
        System.out.println(t1.getState());
    }

    public synchronized void staticResource(String action) throws InterruptedException {
        if (action.equals("대기")) wait();
    }

}
