package com.sycoprime.movecraft.plugins;

import com.sycoprime.movecraft.Central;

import org.bukkit.entity.Player;

/**
 * Permissions support file to interface Nijikokun's Permissions plugin to MoveCraft
*/

public class PermissionInterface {
	
	public static boolean CheckPermission(Player player, String command) {		
		command = command.replace(" ", ".");
		Central.debugMessage("Checking if " + player.getName() + " can " + command, 3);
		
		if(Central.configSetting("RequireOp").equalsIgnoreCase("true") && !player.isOp()) {
				Central.debugMessage("Op is required, and " + player.getDisplayName() + " doesn't have it.", 4);
				return false;
		}
				
		else if(player.hasPermission(command) || player.isOp()) {
			Central.debugMessage("Player has permissions: " + 
					player.hasPermission(command), 3);
	    	Central.debugMessage("Player isop: " + 
		    			player.isOp(), 3);
		    	return true;
		    }
		
		player.sendMessage("You do not have permission to preform " + command);
		return false;
		}
}
