package com.xenathorstudios.x1engine.util;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Runs thread pools
 */
public class PooledThread extends Thread {

    private static SerialGenerator threadID = new SerialGenerator(0);
    private ThreadPool pool;

    public PooledThread(ThreadPool pool) {
        super(pool, "PooledThread-" + threadID.next());
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
