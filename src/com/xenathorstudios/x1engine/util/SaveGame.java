package com.xenathorstudios.x1engine.util;

import java.io.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Writes necessary game data to a save file and loads the data into the game
 */
public class SaveGame {

    private FileHandler fileHandler = new FileHandler();

    /**
     * Saves the specified data to a save file
     * @param filepath the save file to use
     */
    public void saveGame(String filepath) {
        /*
        Here's an example of saving data and writing comments to a save file:

        fileHandler.comment(filepath, "This is a comment"); //Writes a comment to the save file
        fileHandler.write(filepath, some_global_data_variable); //Writes data entry #1
        fileHandler.comment(filepath, "This is another comment"); //Writes another comment to the save file
        fileHandler.write(filepath, some_other_global_data_variable); //Writes data entry #2

        Let's say we need to save an array of items the player possesses.
        for(int i = 0; i < item_count; i++) {
            fileHandler.write(filepath, items[i]);
        }
        And so on until everything that needs to be saved is saved
        */
    }

    /**
     * Loads previous save data into present game
     * @param filepath the save file to use
     */
    public void loadGame(String filepath) {
        try {
            /*
            The format for loading should be as follows:
            global_data_1 = fileHandler.getNextDataLine(filepath);
            global_data_2 = fileHandler.getNextDataLine(filepath);
            And so on until all the data is initialized
             */
            int data = Integer.parseInt(fileHandler.getNextDataLine(filepath)); //Placeholder so that the try/catch block can exist peacefully. DON'T FORGET TO REMOVE THIS LINE!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
