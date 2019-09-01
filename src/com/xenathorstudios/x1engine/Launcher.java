package com.xenathorstudios.x1engine;

import com.xenathorstudios.x1engine.util.ThreadPool;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Launches the game by running all game threads
 */
public class Launcher {
    public static void main(String[] args) {

        //Add thread instances here
        ThreadPool pool = new ThreadPool(0); //Change to number of threads
        //Run thread instances in pool tasks here
        pool.join();
    }
}
