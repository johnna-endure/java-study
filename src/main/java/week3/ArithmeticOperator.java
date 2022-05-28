package week3;

public class ArithmeticOperator {

    public static void main(String[] args) {

        CustomPrinter p = (name) -> {
            System.out.println("hello, "+ name);
        };


        p.print("cws");
    }
}

interface CustomPrinter {
    void print(String name);
}
