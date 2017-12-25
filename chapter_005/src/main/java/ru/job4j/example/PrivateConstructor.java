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

/*package ru.job4j.example;
public class PrivateConstructor {
    int n;
    boolean valueSet = false;
    synchronized int get() {
        while (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Искл перехвачено");
            }
        System.out.println("Получено: " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n) {
        while (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Искл перехвачено");
            }
        this.n = n;
        valueSet = true;
        System.out.println("Отправлено: " + n);
        notify();
    }
}

class Producer implements Runnable {
    PrivateConstructor q;
    Producer(PrivateConstructor q) {
        this.q = q;
        new Thread(this, "Поставщик").start();
    }
    public void run() {
        int i = 0;
        while(true) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    PrivateConstructor q;
    Consumer(PrivateConstructor q) {
        this.q = q;
        new Thread(this, "Потребитель").start();
    }
    public void run() {
        while(true) {
            q.get();
        }
    }
}

class PCFixed {
    public static void main(String[] args) {
        PrivateConstructor q = new PrivateConstructor();
        new Producer(q);
        new Consumer(q);
        System.out.println("Нажмите Сtrl+C");
    }
}
*/
