package week5;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ThisTest {
    String message = "global";

    @Test
    public void thisTest() {
//        Runnable printRunnable = () -> {
//            System.out.println(this.message);
//        };

        Runnable printRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(message);
//                System.out.println(this.message);
            }
        };


        var example = new Example();
        example.print(printRunnable);
    }
}


class Example {
    String message = "inner";

    public void print(Runnable runnable) {
        runnable.run();
    }
}