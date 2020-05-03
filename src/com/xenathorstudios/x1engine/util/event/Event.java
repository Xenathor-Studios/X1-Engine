package com.xenathorstudios.x1engine.util.event;

import javax.swing.*;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Consists of code pertaining to Events
 * Events are sent by engine subsystems to globally tell other subsystems that something has happened
 * An Event may also be known as a Command or Message
 */
public class Event {

    private EventType eType;
    private EventArg[] eArgs;
    private final String NULL_KEY = "null_key";

    protected class EventArg {

        private String key;
        private Object data;

        /**
         * Empty Constructor
         */
        public EventArg() {
            this.key = NULL_KEY;
            this.data = null;
        }

        /**
         * Constructs a new Event Argument object
         * @param key the identifier of the object
         * @param data the data corresponding to the key
         */
        public EventArg(String key, Object data) {
            this.key = key;
            this.data = data;
        }

        String getKey() {
            return key;
        }

        void setKey(String key) {
            this.key = key;
        }

        Object getData() {
            return data;
        }

        void setData(Object data) {
            this.data = data;
        }
    }

    /**
     * Empty Constructor
     */
    public Event() {
        this.eType = EventType.nullEvent;
        this.eArgs = new EventArg[eType.getMaxEventArgs()];
        for(EventArg eArg : eArgs) {
            eArg.setKey(NULL_KEY);
            eArg.setData(null);
        }
    }

    /**
     * Constructs an Event
     * @param eType the type of event being constructed
     */
    public Event(EventType eType) {
        this.eType = eType;
        this.eArgs = new EventArg[eType.getMaxEventArgs()];
        for(EventArg eArg : eArgs) {
            eArg.setKey(NULL_KEY);
            eArg.setData(null);
        }
    }

    /**
     * Adds an Event Argument to the Event that called this method
     * @param key the key of the argument to be added
     * @param data the data of the corresponding key
     */
    public void addArgument(String key, Object data) {
        for(EventArg eArg : eArgs) {
            if (eArg.getKey().equals(NULL_KEY)) {
                eArg.setKey(key);
                eArg.setData(data);
                break;
            }
        }
    }

    /**
     * Removes all Event Arguments from the Event that called this method that have the specified key and data
     * @param key the key of the argument(s) to be removed
     * @param data the data of the corresponding key
     */
    public void removeArgument(String key, Object data) {
        for(EventArg eArg : eArgs) {
            if (eArg.getKey().equals(key) && eArg.getData().equals(data)) {
                eArg.setKey(NULL_KEY);
                eArg.setData(null);
                break;
            }
        }
    }

    public EventArg getArgument(int index) {
        return eArgs[index];
    }

    public void setArgument(EventArg argument, int index) {
        this.eArgs[index] = argument;
    }

    public String getArgumentKey(int index) {
        return eArgs[index].getKey();
    }

    public void setArgumentKey(String key, int index) {
        this.eArgs[index].setKey(key);
    }

    public Object getArgumentData(int index) {
        return eArgs[index].getData();
    }

    public void setArgumentData(Object data, int index) {
        this.eArgs[index].setData(data);
    }

    public EventArg[] getAllArguments() {
        return eArgs;
    }

    public void setAllArguments(EventArg[] arguments) {
        if(arguments.length > eArgs.length) {
            JFrame exitFrame = new JFrame();
            exitFrame.pack();
            exitFrame.setLocationRelativeTo(null);
            exitFrame.setVisible(true);
            JOptionPane.showMessageDialog(exitFrame, "Passed array longer than called array. Error code 4.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        } else if(arguments.length < eArgs.length) {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i] = arguments[i];
            }
            for(int i = arguments.length; i < eArgs.length; i++) {
                this.eArgs[i] = new EventArg();
            }
        } else {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i] = arguments[i];
            }
        }
    }

    public String[] getAllArgumentKeys() {
        String[] s = new String[eArgs.length];
        for(int i = 0; i < eArgs.length; i++) {
            s[i] = eArgs[i].getKey();
        }
        return s;
    }

    public void setAllArgumentKeys(String[] arguments) {
        if(arguments.length > eArgs.length) {
            JFrame exitFrame = new JFrame();
            exitFrame.pack();
            exitFrame.setLocationRelativeTo(null);
            exitFrame.setVisible(true);
            JOptionPane.showMessageDialog(exitFrame, "Passed array longer than called array. Error code 4.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        } else if(arguments.length < eArgs.length) {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i].setKey(arguments[i]);
            }
            for(int i = arguments.length; i < eArgs.length; i++) {
                this.eArgs[i].setKey(NULL_KEY);
            }
        } else {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i].setKey(arguments[i]);
            }
        }
    }

    public Object[] getAllArgumentData() {
        Object[] o = new Object[eArgs.length];
        for(int i = 0; i < eArgs.length; i++) {
            o[i] = eArgs[i].getData();
        }
        return o;
    }

    public void setAllArgumentData(Object[] arguments) {
        if(arguments.length > eArgs.length) {
            JFrame exitFrame = new JFrame();
            exitFrame.pack();
            exitFrame.setLocationRelativeTo(null);
            exitFrame.setVisible(true);
            JOptionPane.showMessageDialog(exitFrame, "Passed array longer than called array. Error code 4.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        } else if(arguments.length < eArgs.length) {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i].setData(arguments[i]);
            }
            for(int i = arguments.length; i < eArgs.length; i++) {
                this.eArgs[i].setData(null);
            }
        } else {
            for(int i = 0; i < arguments.length; i++) {
                this.eArgs[i].setData(arguments[i]);
            }
        }
    }

    public String getEventType() {
        return eType.getType();
    }

    public void setEventType(String type) {
        this.eType.setType(type);
    }
}
