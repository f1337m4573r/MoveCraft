/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sycoprime.movecraft.managers;

import com.sycoprime.movecraft.Central;

/**
 *
 * @author alexanderchristie
 */
public class DebugManager {
    private boolean debugMode = true;
    
    private DebugManager() {
    }
    
    public static DebugManager getInstance() {
        return DebugManagerHolder.INSTANCE;
    }
    
    private static class DebugManagerHolder {

        private static final DebugManager INSTANCE = new DebugManager();
    }
    
    public void toggleDebug() {
        debugMode = !debugMode;
        System.out.println("Debug mode set to " + debugMode);
    }

    public boolean debugMessage(String message, int messageLevel) {
        /* Message Levels:
         * 0: Error
         * 1: Something I'm currently testing
         * 2: Something I think I just fixed
         * 3: Something I'm pretty sure is fixed
         * 4: Supporting information
         * 5: Nearly frivolous information
         */
        if(Integer.parseInt(Central.configSetting("LogLevel")) >= messageLevel){
            if(debugMode){
                System.out.println(message);
            }
        }
        
        return this.debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    
}
