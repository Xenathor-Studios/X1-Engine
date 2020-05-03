package com.xenathorstudios.x1engine.util.event;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Superclass for handling Events at the system level
 */
public abstract class EventHandler {

    /**
     * Abstract method for handling Events at the system level
     * The Queue sends Events to the appropriate system's handle() method
     * It is recommended to make handle() one large switch statement where the cases are Event Types
     * @param e the event to handle
     */
    public abstract void handle(Event e);
}
