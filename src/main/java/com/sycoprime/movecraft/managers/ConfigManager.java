/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sycoprime.movecraft.managers;

import com.sycoprime.movecraft.Central;
import com.sycoprime.movecraft.Craft;
import com.sycoprime.movecraft.CraftType;
import com.sycoprime.movecraft.config.ConfigFile;
import java.io.File;
import java.io.File;

/**
 *
 * @author alexanderchristie
 */
public class ConfigManager {
    private ConfigFile configFile;
    
    private ConfigManager() {
    }
    
    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }
    
    private static class ConfigManagerHolder {

        private static final ConfigManager INSTANCE = new ConfigManager();
    }
    
    public String configSetting(String setting) {
        if(configFile.ConfigSettings.containsKey(setting))
                return configFile.ConfigSettings.get(setting);
        else {
                System.out.println("AJCStriker needs to be notified that a non-existing config setting '" + setting + 
                                "' was attempted to be accessed. Please email alex@countercraft.net.");
                return "";
        }
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }
    
    public void loadProperties() {
		configFile = new ConfigFile();

		File dir = Central.getPluginInstance().getDataFolder();
		if (!dir.exists())
			dir.mkdir();

		CraftType.loadTypes(dir);
		//This setting was removed as of 0.6.9, craft type file creation has been commented out of the whole thing,
			//craft type files are to be distributed with the plugin 
		CraftType.saveTypes(dir);
	}
    
}
