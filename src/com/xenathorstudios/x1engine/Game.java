package com.xenathorstudios.x1engine;

import javax.swing.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Consists of Game Loop and related functions
 */
public class Game implements Runnable {

    /*
     * Declare save files like the following, and format them similar to example_save.sve
     * Save files should have extension '.sve'
     * This comment and following variable may move location.
     */
    private final String EXAMPLE_SAVE = "./res/save/example_save.sve";
    private final String TITLE;
    private final double MS_PER_FRAME = 16.666666; //60FPS
    private boolean running = false;

    Game(String title) {
        this.TITLE = title;
    }

    private void init() {
        //Anything that needs to be initialized prior to the game starting goes in here
        running = true;
    }

    private void update() {
        //Processes UI
        //Does physics sim
        //Updates game entities/objects one tick
    }

    private void render() {
        //Renders, obviously
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
                if((long) (MS_PER_FRAME - (currentTime - lastTime)/1000000) >= 0) {
                    Thread.sleep((long) (MS_PER_FRAME - (currentTime - lastTime) / 1000000));
                } else {
                    JFrame exitFrame = new JFrame();
                    exitFrame.pack();
                    exitFrame.setLocationRelativeTo(null);
                    exitFrame.setVisible(true);
                    JOptionPane.showMessageDialog(exitFrame, "Tick took too long: Tick optimization required. Error code 2.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
