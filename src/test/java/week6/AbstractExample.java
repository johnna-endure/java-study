package week6;

public class AbstractExample {

    final String hello = "";

}


interface A {

}

abstract class B {
    public abstract String hello();

    public void printHello() {
        System.out.println(hello());
    }
}