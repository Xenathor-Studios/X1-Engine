package com.xenathorstudios.x1engine.util.event;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * 
 */
public enum EventType {

    //Construct all your Event Types here, with the format: <EVENT NAME>_EVENT("<name>"),
    NULL_EVENT();

    private String type;

    /**
     * Empty Constructor
     */
    EventType() {
        this.type = "null";
    }

    /**
     * Constructs an Event Type
     * @param type the type of the event
     */
    EventType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}
