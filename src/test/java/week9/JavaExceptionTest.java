package week9;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JavaExceptionTest {


    @Test
    public void test() {
        try {
            throwException();
        } catch (IOException e) {
            System.out.println("IO Exception");
        } finally {
            System.out.println("finally");
        }
    }

    public void throwException() throws IOException {
        throwIOException();

        throw new RuntimeException();
    }

    public void throwIOException() throws IOException {
        throw new IOException();

    }
}

class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}

