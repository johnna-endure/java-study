package week6;


public class JavaInterfaceExample {

    public static void main(String[] args) {
        Parent p = new Child();
        p.hello();
    }

}


class Parent {
    public Parent() {
    }

    public String hello() {
        return "parent";
    }
//    public abstract String hello();
}

class Child extends Parent {
    public Child() {
//        super();
    }

    @Override
    public String hello() {
        return "child";
    }
}

interface AB {

    String hello();

    default void printHello() {
        System.out.println(hello());
    }

}