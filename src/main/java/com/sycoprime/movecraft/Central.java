package com.sycoprime.movecraft;

import com.sycoprime.movecraft.managers.ConfigManager;
import com.sycoprime.movecraft.managers.DebugManager;
import org.bukkit.Server;

public class Central {
    
    public static MoveCraft getPluginInstance(){
        return MoveCraft.getInstance();
    }
    
    public static String getVersion(){
        return "V 8.0 Alpha";
    }
    
    public static String getPluginName(){
        return "Movecraft CCDev Fork";
    }
        
    public static Server getPluginServer(){
        return getPluginInstance().getServer();
    }
    
    public static DebugManager getDebugManager(){
        return DebugManager.getInstance();
    }
    public static void debugMessage(String message, int messageLevel){
        getDebugManager().debugMessage(message, messageLevel);
    }
    
    public static ConfigManager getConfigManager(){
        return  ConfigManager.getInstance();
    }
    
    public static String configSetting(String setting) {
        return getConfigManager().configSetting(setting);
    }
}
