package com.xenathorstudios.x1engine.util.event;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * 
 */
public enum EventType {

    //Construct all your Event Types here, with the format: <EVENT NAME>_EVENT("<name>", #),
    NULL_EVENT();

    private String type;
    private int maxEventArgs;

    /**
     * Empty Constructor
     */
    EventType() {
        this.type = "null";
        this.maxEventArgs = 0;
    }

    /**
     * Constructs an Event Type
     * @param type the type of the event
     * @param maxEventArgs the maximum number of Event Arguments the type has
     */
    EventType(String type, int maxEventArgs) {
        this.type = type;
        this.maxEventArgs = maxEventArgs;
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    int getMaxEventArgs() {
        return maxEventArgs;
    }

    void setMaxEventArgs(int maxEventArgs) {
        this.maxEventArgs = maxEventArgs;
    }
}
