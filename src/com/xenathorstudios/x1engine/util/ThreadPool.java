package com.xenathorstudios.x1engine.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Thread pooling class
 */
public class ThreadPool extends ThreadGroup {

    private boolean isThreadAlive;
    private int id;
    private List<Runnable> taskQueue;

    /**
     * Constructs a thread pool
     * @param numThreads the number of threads in the pool
     */
    public ThreadPool(int numThreads) {
        super("ThreadPool");
        setDaemon(true);
        taskQueue = new LinkedList<Runnable>();
        isThreadAlive = true;
        for(int i = 0; i < numThreads; i++) {
            new ThreadPoolHandler(this).start();
        }
    }

    /**
     * Runs the task specified
     * @param task the task to run
     */
    public synchronized void runTask(Runnable task) {
        if(!isThreadAlive) { throw new IllegalStateException("ThreadPool is dead"); }
        if(task != null) {
            taskQueue.add(task);
            notify();
        }
    }

    /**
     * Force terminates all threads
     */
    public synchronized void close() {
        if(!isThreadAlive) { return; }
        isThreadAlive = false;
        taskQueue.clear();
        interrupt();
    }

    /**
     * Closes the thread pool while waiting for tasks to finish
     */
    public void join() {
        synchronized(this) {
            isThreadAlive = false;
            notifyAll();
        }
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for(int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the task at the top of the task queue
     * @return the first task
     * @throws InterruptedException
     */
    synchronized Runnable getTask() throws InterruptedException {
        while(taskQueue.size() == 0) {
            if(!isThreadAlive) { return null; }
            wait();
        }
        return taskQueue.remove(0);
    }

    public class ThreadPoolHandler extends Thread {

        private ThreadPool pool;

        ThreadPoolHandler(ThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                Runnable task = null;
                try {
                    task = pool.getTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(task == null) { return; }
                try {
                    task.run();
                } catch(Throwable t) {
                    pool.uncaughtException(this, t);
                }
            }
        }
    }
}
