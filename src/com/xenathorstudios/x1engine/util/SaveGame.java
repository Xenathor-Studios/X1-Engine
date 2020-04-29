package com.xenathorstudios.x1engine.util;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Writes necessary game data to a save file
 */
public class SaveGame {
    private final String SAVE_PATH = "./res/save/save.sve";
    private final String SAVE_CFG = "./res/save/save.cfg";
    private boolean checkPoint;
    private final int IS_CHECK_POINT = 13;

    private boolean isCheckPoint() throws IOException {
        if(Integer.parseInt(readLine(IS_CHECK_POINT)) == 1) { checkPoint = true; }
        else if(Integer.parseInt(readLine(IS_CHECK_POINT)) == 0) { checkPoint = false; }
        else {
            JFrame exitFrame = new JFrame();
            exitFrame.pack();
            exitFrame.setLocationRelativeTo(null);
            exitFrame.setVisible(true);
            JOptionPane.showMessageDialog(exitFrame, "Check Point boolean has an improper value. Ensure that IS_CHECK_POINT in SaveGame.java " +
                    "points to the correct line number in save.cfg!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return checkPoint;
    }

    /**
     * Reads the save file and returns the text within it
     * @return text the text of the file
     * @throws IOException
     */
    public String read() throws IOException {
        File saveFile = new File(SAVE_PATH);
        FileReader reader = new FileReader(saveFile);
        BufferedReader bReader = new BufferedReader(reader);

        String text = "";
        String line = bReader.readLine();

        while (line != null) {
            text += line;
            line = bReader.readLine();
        }

        return text;
    }

    /**
     * Reads a specific line of a file
     * @return text the text of the line specified
     * @throws IOException
     */
    public String readLine(int lineNumber) throws IOException {
        File saveFile = new File(SAVE_PATH);
        FileReader reader = new FileReader(saveFile);
        BufferedReader bReader = new BufferedReader(reader);

        for(int i = 0; i < lineNumber; i++) {
            bReader.readLine();
        }

        return bReader.readLine();
    }

    /**
     * Counts the number of lines of a file
     * @return lineCount the number of lines
     * @throws IOException
     */
    public int lineCounter() throws IOException {
        File saveFile = new File(SAVE_PATH);
        LineNumberReader lnr = new LineNumberReader(new FileReader(saveFile));
        while (lnr.skip(Long.MAX_VALUE) > 0);
        int lineCount = lnr.getLineNumber();

        return lineCount;
    }

    /**
     * Writes to the next open line of a file
     */
    public void write(String toWrite) {
        File saveFile = new File(SAVE_PATH);
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(saveFile, true))) {
            bWriter.write(toWrite + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
