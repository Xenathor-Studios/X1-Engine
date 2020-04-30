package com.xenathorstudios.x1engine.util;

import java.io.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * 
 */
public class FileHandler {

    private int cur_line = 0;

    /**
     * Empty Constructor
     */
    FileHandler() {

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
     public void write(String filepath, String toWrite) {
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
     public void comment(String filepath, String message) {
        File saveFile = new File(filepath);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write("# " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
