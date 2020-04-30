package com.xenathorstudios.x1engine.util;

import java.io.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Writes necessary game data to a save file
 */
public class SaveGame {

    private int cur_line = 0;

    /**
     * Saves the specified data to a save file
     * @param filepath the save file to use
     */
    public void saveGame(String filepath) {
        /*
        Here's an example of saving data and writing comments to a save file:

        comment(filepath, "This is a comment"); //Writes a comment to the save file
        write(filepath, some_global_data_variable); //Writes data entry #1
        comment(filepath, "This is another comment"); //Writes another comment to the save file
        write(filepath, some_other_global_data_variable); //Writes data entry #2

        Let's say we need to save an array of items the player possesses.
        for(int i = 0; i < item_count; i++) {
            write(filepath, items[i]);
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
            global_data_1 = getNextDataLine(filepath);
            global_data_2 = getNextDataLine(filepath);
            And so on until all the data is initialized
             */
            int data = Integer.parseInt(getNextDataLine(filepath)); //Placeholder so that the try/catch block can exist peacefully. DON'T FORGET TO REMOVE THIS LINE!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a specific line of a file
     * @param filepath the save file to read
     * @param lineNumber the number of the line to read
     * @return text the text of the line specified
     * @throws IOException
     */
    private String readLine(String filepath, int lineNumber) throws IOException {
        File saveFile = new File(filepath);
        FileReader reader = new FileReader(saveFile);
        BufferedReader bReader = new BufferedReader(reader);

        for(int i = 0; i < lineNumber; i++) {
            bReader.readLine();
        }

        return bReader.readLine();
    }

    /**
     * Counts the number of lines of a file
     * @param filepath the save file to count
     * @return the number of lines
     * @throws IOException
     */
    private int lineCounter(String filepath) throws IOException {
        File saveFile = new File(filepath);
        LineNumberReader lnr = new LineNumberReader(new FileReader(saveFile));
        while (lnr.skip(Long.MAX_VALUE) > 0);

        return lnr.getLineNumber() + 1;
    }

    /**
     * Gets the next uncommented line from the save file. Note that this returns a String!
     * @param filepath the save file to read
     * @return the next line of data
     * @throws IOException
     */
    private String getNextDataLine(String filepath) throws IOException {
        String line = readLine(filepath, cur_line);
        try {
            if(line.charAt(0) == '#') {
                if(cur_line > lineCounter(filepath)) {
                    cur_line = 0;
                    return "";
                }
                cur_line++;
                getNextDataLine(filepath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cur_line++;
        return line;
    }

    /**
     * Writes to the next open line of a file
     * @param filepath the save file to write to
     * @param toWrite the info to write
     */
    private void write(String filepath, String toWrite) {
        File saveFile = new File(filepath);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write(toWrite + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a comment to the next open line of a file
     * @param filepath the save file to write to
     * @param message the comment to write
     */
    private void comment(String filepath, String message) {
        File saveFile = new File(filepath);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write("# " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
