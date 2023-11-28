package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = Logger.getLogger("Fixed Thread Pool Logger");
    private Thread[] threads;
    private final BlockingQueue<Runnable> tasksQueue;
    private boolean isStopped = false;

    public FixedThreadPool() {
        tasksQueue = new LinkedBlockingQueue<>();
    }

    // just initialize threads
    public void create(int nThreads) {
        threads = new Thread[nThreads];
    }

    // starting the threads that are waiting for tasks
    @Override
    public void start() {
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(this::runTask);
            threads[i].start();
        }
    }

    // adding task to queue and notify it (not empty now)
    @Override
    public void execute(Runnable runnable) {
        synchronized (tasksQueue) {
            tasksQueue.add(runnable);
            tasksQueue.notify();
        }
    }

    // close all threads
    @Override
    public void close() throws InterruptedException {
        isStopped = true;
        for (var thread : threads) {
            thread.join();
        }
    }

    // endless loop, where we are waiting for elements in queue
    // and try to execute the task on the thread that captured the queue first
    private void runTask() {
        Runnable task;

        while (!isStopped || !tasksQueue.isEmpty()) {
            synchronized (tasksQueue) {
                while (tasksQueue.isEmpty()) {
                    try {
                        tasksQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                task = tasksQueue.poll();
            }
            try {
                task.run();
            } catch (RuntimeException e) {
                LOGGER.info(e.getMessage());
            }
        }
    }
}
