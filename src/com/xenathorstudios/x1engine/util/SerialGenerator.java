package com.xenathorstudios.x1engine.util;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Used for generating unique serial IDs for sprites and textures
 * This class may be removed
 */
public class SerialGenerator {

    private static long imageID;
    private int threadBaseID; //threadBaseID starts at 0

    public SerialGenerator(int id) {
        this.threadBaseID = id;
    }

    public int next() {
        return threadBaseID++;
    }

    /**
     * Generates a serial number for a specific sprite/frame for identification purposes
     * @param spriteSheetName the name of the Sprite Sheet in question
     * @param imageName the name of the image in question
     * @param col the top-left column of the image
     * @param row the top-left row of the image
     * @return the generated serial number
     */
    public static long serialGeneratorForImages(String spriteSheetName, String imageName, int col, int row) {

        int ssName = spriteSheetName.length();
        spriteSheetName = Integer.toString(ssName);
        int imgName = imageName.length();
        imageName = Integer.toString(imgName);
        String columnNum = Integer.toString(col);
        String rowNum = Integer.toString(row);
        String serial = "00" + spriteSheetName + "0" + columnNum + rowNum + "0" + imageName;
        imageID = Integer.parseInt(serial);
        return imageID;
    }

    public int getCurThreadBaseID() {
        return threadBaseID;
    }
}
