package com.xenathorstudios.x1engine.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Thread pooling class
 */
public class ThreadPool extends ThreadGroup {

    public boolean isThreadAlive;
    private static SerialGenerator poolID = new SerialGenerator(0);
    private int id;
    private List<Runnable> taskQueue;

    /**
     * Constructs a thread pool
     * @param numThreads the number of threads in the pool
     */
    public ThreadPool(int numThreads) {
        super("ThreadPool-" + poolID.next());
        this.id = poolID.getCurThreadBaseID();
        setDaemon(true);
        taskQueue = new LinkedList<Runnable>();
        isThreadAlive = true;
        for(int i = 0; i < numThreads; i++) {
            new PooledThread(this).start();
        }
    }

    /**
     * Runs the task specified
     * @param task the task to run
     */
    public synchronized void runTask(Runnable task) {
        if(!isThreadAlive) { throw new IllegalStateException("ThreadPool-" + id + " is dead"); }
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

    protected synchronized Runnable getTask() throws InterruptedException {
        while(taskQueue.size() == 0) {
            if(!isThreadAlive) { return null; }
            wait();
        }
        return taskQueue.remove(0);
    }
}
