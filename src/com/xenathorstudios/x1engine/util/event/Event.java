package com.xenathorstudios.x1engine.util.event;

import java.util.HashMap;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Consists of code pertaining to Events
 * Events are sent by engine subsystems to globally tell other subsystems that something has happened
 * An Event may also be known as a Command or Message
 */
public class Event {

    private EventType eType;
    private HashMap<String, Object> eArgs;
    private final String NULL_KEY = "null_key";

    /**
     * Empty Constructor
     */
    public Event() {
        this.eType = EventType.NULL_EVENT;
        this.eArgs = new HashMap<>();
        eArgs.put(NULL_KEY, null);
    }

    /**
     * Constructs an Event
     * @param eType the type of event being constructed
     */
    public Event(EventType eType) {
        this.eType = eType;
        this.eArgs = new HashMap<>();
    }

    /**
     * Adds an Event Argument to the Event that called this method
     * @param key the key of the argument to be added
     * @param data the data of the corresponding key
     */
    public void addArgument(String key, Object data) {
        eArgs.put(key, data);
    }

    /**
     * Remove the Event Argument from the Event that called this method that has the specified key and data
     * @param key the key of the argument(s) to be removed
     * @param data the data of the corresponding key
     */
    public void removeArgument(String key, Object data) {
        eArgs.remove(key, data);
    }

    /**
     * Gets the key of the associated data object
     * @param data the object associated with the key to get
     * @return the key of interest or NULL_KEY if no such key was found
     */
    public String getArgumentKey(Object data) {
        for(String key : eArgs.keySet()) {
            if(eArgs.get(key) == data) {
                return key;
            }
        }
        return NULL_KEY;
    }

    public Object getArgumentData(String key) {
        return eArgs.get(key);
    }

    public void setArgumentData(Object data, String key) {
        eArgs.replace(key, data);
    }

    public String[] getAllArgumentKeys() {
        String[] s = new String[eArgs.size()];
        int i = 0;
        for(String key : eArgs.keySet()) {
            s[i] = key;
            i++;
        }
        return s;
    }

    public Object[] getAllArgumentData() {
        Object[] o = new Object[eArgs.size()];
        int i = 0;
        for(String key : eArgs.keySet()) {
            o[i] = eArgs.get(key);
            i++;
        }
        return o;
    }

    public void setAllArgumentData(Object[] data) {
        int i = 0;
        for(String key : eArgs.keySet()) {
            eArgs.replace(key, data[i]);
            i++;
        }
    }

    public String getEventTypeString() {
        return eType.getType();
    }

    public void setEventTypeString(String type) {
        this.eType.setType(type);
    }

    public EventType getEventType() {
        return eType;
    }

    public void setEventType(EventType type) {
        this.eType = type;
    }
}
