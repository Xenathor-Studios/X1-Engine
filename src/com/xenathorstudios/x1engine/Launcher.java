package com.xenathorstudios.x1engine;

import com.xenathorstudios.x1engine.util.FileHandler;
import com.xenathorstudios.x1engine.util.ThreadPool;
import com.xenathorstudios.x1engine.util.event.Queue;
import java.io.IOException;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Launches the game by initializing config data and running all game threads
 */
public class Launcher {

    private static final String CFG_PATH = "./x1engine.cfg";
    private static FileHandler fileHandler = new FileHandler();
    private static String title;
    private static int max_threads;

    public static void main(String[] args) {
        //Evaluates configuration data
        try {
            title = fileHandler.getNextDataLine(CFG_PATH);
            max_threads = Integer.parseInt(fileHandler.getNextDataLine(CFG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add thread instances here
        Game game = new Game(title); //Primary thread, takes care of game objects, AI, physics, and gameplay
        Queue queue = new Queue(); //Secondary thread, takes care of events (commands, messages)
        //Planned thread instances: Media Player, Render

        ThreadPool pool = new ThreadPool(max_threads);
        //Run threads here
        pool.runTask(game);
        pool.runTask(queue);

        pool.join();
    }
}
