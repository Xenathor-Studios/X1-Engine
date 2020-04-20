package com.xenathorstudios.x1engine;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Consists of Game Loop and related functions
 */
public class Game implements Runnable {

    private final String TITLE;
    private final double MS_PER_FRAME = 16.666666; //60FPS
    private boolean running = false;

    public Game (String title) {
        this.TITLE = title;
    }

    public void init() {
        running = true;
    }

    public void update() {

    }

    public void render() {

    }

    //Game loop to be Event-Based with fixed update time step with synchronization
    //Since this is for 2D graphics, we can reliably assume that it will never take longer than 16ms to Update and Render
    public void run() {
        init();

        while(running) {
            long lastTime = System.nanoTime();
            update();
            render();
            long currentTime = System.nanoTime();
            try {
                Thread.sleep((long) (MS_PER_FRAME - (currentTime - lastTime)/1000000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
