package com.xenathorstudios.x1engine.util.event;

import javax.swing.*;
import java.util.ArrayList;
import com.xenathorstudios.x1engine.util.event.EventType;

/**
 * Project: X1 Engine
 * @author Maxwell "M_Dragon" Battles
 * Handles all Events at the global level
 * TODO: See handleQueue() comment
 */
public class Queue implements Runnable {

    private final double MS_PER_FRAME = 16.666666; //60FPS -- synced with Game
    private boolean running = false;
    private ArrayList<Event> eventQueue;

    /**
     * Empty Constructor
     */
    public Queue() {
        eventQueue = new ArrayList<Event>();
    }

    /**
     * Constructs a Queue
     * @param list the ArrayList of Events the Queue is to store
     */
    public Queue(ArrayList<Event> list) {
        this.eventQueue = list;
    }

    private void init() {
        running = true;
    }

    public void run() {
        init();

        while(running) {
            if(!isQueueEmpty()) {
                handleQueue();
                continue;
            }
            try {
                Thread.sleep((long) MS_PER_FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks to see if the Queue is empty
     * @return true if the Queue is empty, false otherwise
     */
    private boolean isQueueEmpty() {
        return eventQueue.isEmpty();
    }

    /**
     * Handles each Event in the Queue until there are no more Events or one tick has passed
     * There could be game loop sync problems with this thread if elapsed is greater than MS_PER_FRAME. A quick solution
     * would be to set elapsed as a static value so that we can predict how many Events will be handled in one tick, but
     * this solution requires that we are positive that handling any Event will not take longer than elapsed. Look into
     * solutions that allow elapsed to be dynamic.
     */
    private void handleQueue() {
        long lastTime;
        long currentTime;
        long elapsed = 0;
        while(!isQueueEmpty() || (elapsed >= (long) MS_PER_FRAME)) {
            lastTime = System.nanoTime();
            /*
             Handle Events in the Queue by sending them to the appropriate system's EventHandler()
             Each case should be formatted as follows:

             case <EVENT NAME>_EVENT:
                <System Name>EventHandler.handle(eventQueue.get(i));
                break;
             */
            switch(eventQueue.get(0).getEventType()) {
                //Add cases here
                /*
                Example case:

                case EVENT:
                    SystemEventHandler.handle(eventQueue.get(0));
                    consume();
                    break;
                 */
                case NULL_EVENT:
                    consume();
                    break;
                default:
                    JFrame exitFrame = new JFrame();
                    exitFrame.pack();
                    exitFrame.setLocationRelativeTo(null);
                    exitFrame.setVisible(true);
                    JOptionPane.showMessageDialog(exitFrame, "Event Type not recognized. Error code 5.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(5);
            }
            currentTime = System.nanoTime();
            elapsed += currentTime - lastTime;
        }
    }

    /**
     * Adds an Event to the Queue
     * @param e the Event to add
     */
    private void add(Event e) {
        eventQueue.add(e);
    }

    /**
     * Consumes, or removes, the Event at the top of the Queue
     * Every Event in the Queue then moves up in line
     */
    private void consume() {
        eventQueue.remove(0);
    }

    public ArrayList<Event> getEventQueue() {
        return eventQueue;
    }
}
