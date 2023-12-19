package edu.hw8.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = Logger.getLogger("Fixed Thread Pool Logger");
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasksQueue;
    private final Map<Long, Boolean> inProcess;

    public FixedThreadPool(int nThreads) {
        tasksQueue = new LinkedBlockingQueue<>();
        threads = new Thread[nThreads];
        inProcess = new HashMap<>();
    }

    // starting the threads that are waiting for tasks
    @Override
    public void start() {
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(this::runTask);
            threads[i].start();
            inProcess.put(threads[i].threadId(), true);
        }
    }

    // adding task to queue and notify it (not empty now)
    @Override
    public void execute(Runnable runnable) {
        synchronized (tasksQueue) {
            tasksQueue.add(runnable);
        }
    }

    // close all threads
    @Override
    public void close() throws InterruptedException {
        while (!tasksQueue.isEmpty()) {
            Thread.sleep(1); // IDK how to optimize it :(
        }

        for (var thread : threads) {
            while (!inProcess.get(thread.threadId())) {
                Thread.sleep(1); // the same problem
            }
            thread.interrupt();
        }

        for (var thread : threads) {
            thread.join();
        }
    }

    // endless loop, where we are waiting for elements in queue
    // and try to execute the task on the thread that captured the queue first
    private void runTask() {
        Runnable task;

        while (!Thread.interrupted()) {
            try {
                task = tasksQueue.take();
            } catch (InterruptedException ignored) {
                return;
            }

            try {
                inProcess.put(Thread.currentThread().threadId(), false);
                task.run();
                inProcess.put(Thread.currentThread().threadId(), true);
            } catch (RuntimeException e) {
                LOGGER.info(e.getMessage());
            }
        }
    }
}
