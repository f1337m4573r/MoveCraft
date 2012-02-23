/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sycoprime.movecraft.managers;

/**
 *
 * @author alexanderchristie
 */
public class EventManager {
    
    private EventManager() {
    }
    
    public static EventManager getInstance() {
        return EventManagerHolder.INSTANCE;
    }
    
    private static class EventManagerHolder {

        private static final EventManager INSTANCE = new EventManager();
    }
}
