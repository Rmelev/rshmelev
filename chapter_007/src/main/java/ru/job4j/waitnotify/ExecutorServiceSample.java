package ru.job4j.waitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example of thread poll calculate.
 */
public class ExecutorServiceSample {
    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        //создать ExecutorService на базе пула из пяти потоков
        /**
         * New thread pool with number of threads equals number of processors in computer.
         */
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /**
         * Task to compute.
         */
        CallableSample cal = new CallableSample();
        /**
         * List of tasks.
         */
        List<Future<String>> taskList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            taskList.add(es.submit(cal));
        }
        for (Future<String> nextElem : taskList) {
            try {
                System.out.println(nextElem.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
    }
}
