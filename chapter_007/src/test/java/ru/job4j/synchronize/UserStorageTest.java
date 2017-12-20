package ru.job4j.synchronize;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing multithreading safety.
 */
public class UserStorageTest implements Runnable {
    /**
     * test storage.
     */
    private UserStorage storage = new UserStorage();
    /**
     * Test.
     */
    @Test
    public void whenHaveOperationsThenCorrectResult() {
        int i = 1;
        while (i < 8) {
            storage.add(new User(i * 11111, i * 1000));
            i++;
        }
        System.out.println("Исходный список:");
        System.out.println(storage.getUsers());
        storage.delete(storage.getUsers().get(66666));
        User newUser = new User(33333, 9000);
        storage.update(newUser);
        storage.transfer(77777, 22222, 500);
        System.out.println("Список после проверки методов delete(), update(), transfer():");
        System.out.println(storage.getUsers());
        assertThat(storage.getUsers().get(77777).getAmount(), is(6500.0));
        assertThat(storage.getUsers().get(22222).getAmount(), is(2500.0));
        Thread thread1 = new Thread(this);
        Thread thread2 = new Thread(this);
        Thread thread3 = new Thread(this);
        Thread thread4 = new Thread(this);
        Thread thread5 = new Thread(this);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(storage.getUsers().get(11111).getAmount());
        System.out.println("Список после проверки потокобезопасного выполнения:");
        System.out.println(storage.getUsers());
        //Если убрать из метода transfer() блок синхронизации тест выдает время от времени отрицательное значение счета.
        assertThat(storage.getUsers().get(11111).getAmount(), is(0.0));
    }

    /**
     * run().
     */
    @Override
    public void run() {
        while (storage.getUsers().get(11111).getAmount() > 0) {
            storage.transfer(11111, 44444, 1);
        }
    }
}