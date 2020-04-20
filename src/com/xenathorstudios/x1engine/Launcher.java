package com.xenathorstudios.x1engine;

import com.xenathorstudios.x1engine.util.ThreadPool;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Launches the game by running all game threads
 */
public class Launcher {

    private static final String TITLE = ""; //Change TITLE to the title of the game
    private static final int MAX_THREADS = 1; //Size of Thread Pool, should not be larger than 4

    public static void main(String[] args) {
        Game game = new Game(TITLE); //Primary thread, takes care of game objects, rendering, and gameplay
        //Add thread instances here
        //Planned thread instances: Music Player
        ThreadPool pool = new ThreadPool(MAX_THREADS);
        pool.runTask(game);
        //Run threads here
        pool.join();
    }
}
