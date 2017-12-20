/*package ru.job4j.example;

public class PrivateConstructor {
    int a = 3;
    String b;

    private PrivateConstructor(int a, String b) {
        this.a = a;
        this.b = b;
    }

    //public PrivateConstructor() {

    //}

    void printHello() {
        System.out.println("Hello!");
    }

    @Override
    public String toString() {
        return "PrivateConstructor{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }

    public static void main(String[] args) {
        //PrivateConstructor ob2 = new PrivateConstructor(7, "moi");
        PrivateConstructor ob = new PrivateConstructor(5, "joi") {

            int n = 10;

            void printHello() {
                System.out.println("Lou");
            }

            void ou(int n) {
                System.out.println(n);
            }
        };

        ob.printHello();
        //ob2.printHello();
        System.out.println(ob);
    }

}*/
