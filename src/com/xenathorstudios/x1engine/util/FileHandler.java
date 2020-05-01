package com.xenathorstudios.x1engine.util;

import java.io.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Handles all reading/writing to an external file
 */
public class FileHandler {

    private int cur_line;

    /**
     * Empty Constructor
     */
    public FileHandler() {
        this.cur_line = 0;
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
    public String getNextDataLine(String filepath) throws IOException {
        String line = "";

        if(cur_line >= lineCounter(filepath)) {
            cur_line = 0;
            return null; //Add error box + master list for error codes (Go through all exceptions)
        }

        for(int i = cur_line; i < lineCounter(filepath); i++) {
            line = readLine(filepath, cur_line);
            if(line.charAt(0) == '#') {
                cur_line = i + 1;
                continue;
            }
            line = readLine(filepath, i);
            cur_line = i + 1;
            break;
        }
        return line;
    }

    /**
     * Writes to the next open line of a file
     * @param filepath the save file to write to
     * @param data the info to write
     */
     public void write(String filepath, String data) {
        File saveFile = new File(filepath);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a comment to the next open line of a file
     * @param filepath the save file to write to
     * @param comment the comment to write
     */
     public void comment(String filepath, String comment) {
        File saveFile = new File(filepath);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write("# " + comment + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
