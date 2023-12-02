package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = Logger.getLogger("Fixed Thread Pool Logger");
    private Thread[] threads;
    private final BlockingQueue<Runnable> tasksQueue;
    private final AtomicBoolean isStopped;

    public FixedThreadPool() {
        tasksQueue = new LinkedBlockingQueue<>();
        isStopped = new AtomicBoolean(false);
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
        while (!tasksQueue.isEmpty()) {
            Thread.sleep(1); // IDK how to optimize it :(
        }

        isStopped.set(true);
        synchronized (tasksQueue) {
            tasksQueue.notifyAll();
        }
        for (var thread : threads) {
            thread.join();
        }
    }

    // endless loop, where we are waiting for elements in queue
    // and try to execute the task on the thread that captured the queue first
    private void runTask() {
        Runnable task;

        while (!isStopped.get()) {
            synchronized (tasksQueue) {
                while (tasksQueue.isEmpty() && !isStopped.get()) {
                    try {
                        tasksQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (isStopped.get()) {
                    return;
                }
                task = tasksQueue.poll();
            }
            try {
                assert task != null;
                task.run();
            } catch (RuntimeException e) {
                LOGGER.info(e.getMessage());
            }
        }
    }
}
