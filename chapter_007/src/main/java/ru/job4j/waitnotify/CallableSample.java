package ru.job4j.waitnotify;

import java.util.concurrent.Callable;

/**
 * Task for thread pool.
 */
public class CallableSample implements Callable<String> {
    /**
     * counter of tasks.
     */
    private int i = 0;

    /**
     * call().
     * @return - String result of task.
     * @throws Exception - Exception.
     */
    public String call() throws Exception {
        return i++ + "-ую работу выполняет: " + Thread.currentThread().getName();
    }
}
