package com.xenathorstudios.x1engine.util.event;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * 
 */
class EventType {

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
     */
    EventType(String type, int maxEventArgs) {
        this.type = type;
        this.maxEventArgs = maxEventArgs;
    }

    String getType() {
        return this.type;
    }

    void setType(String type) {
        this.type = type;
    }

    int getMaxEventArgs() {
        return this.maxEventArgs;
    }

    void setMaxEventArgs(int maxEventArgs) {
        this.maxEventArgs = maxEventArgs;
    }

    //Construct all your Event Types here
    public static EventType nullEvent = new EventType();
}
