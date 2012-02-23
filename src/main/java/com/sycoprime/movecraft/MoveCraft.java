package com.sycoprime.movecraft;

import java.util.logging.*;
import java.io.File;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.sycoprime.movecraft.config.ConfigFile;
// disabled until I get a handle on the group perms -- dlmarti
//import com.sycoprime.movecraft.plugins.PermissionInterface;


/**
 * MoveCraft plugin for Hey0 mod (hMod) by Yogoda
 * Ported to Bukkit by SycoPrime
 *
 * You are free to modify it for your own server
 * or use part of the code for your own plugins.
 * You don't need to credit me if you do, but I would appreciate it :)
 *
 * You are not allowed to distribute alternative versions of MoveCraft without my consent.
 * If you do cool modifications, please tell me so I can integrate it :)
 */

public class MoveCraft extends JavaPlugin {

	private static MoveCraft instance;

	public static Logger logger = Logger.getLogger("Minecraft");
        
        
        public static MoveCraft getInstance(){
            return instance;
        }
        
	@Override
	public void onLoad() {
            super.onLoad();
            instance = this;
	}
        
	public void onEnable() {
		// getServer().getScheduler().scheduleSyncDelayedTask(this, loadSensors, 20*5); I really dont know what this is meant to do - AJCStriker

		BlocksInfo.loadBlocksInfo();
		Central.getConfigManager().loadProperties();

		System.out.println("[" + Central.getPluginName() + "] " + Central.getVersion() + " is enabled");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println("["+ Central.getPluginName() + "] " + Central.getVersion() + " has been disabled");
	}

	public void releaseCraft(Player player, Craft craft) {
		if (craft != null) {
			player.sendMessage(ChatColor.YELLOW + craft.type.sayOnRelease);
			Craft.removeCraft(craft);
			
			
			System.out.println("PILOT RELEASE :" + player.getName() + " at (" + 
					Integer.toString(craft.minX) + "," + 
					Integer.toString(craft.minY) + "," + 
					Integer.toString(craft.minZ) +
					")");
			
			if(Central.getDebugManager().isDebugMode()){
				craft.Destroy();
                        }
		} else
			player.sendMessage(ChatColor.YELLOW + "You don't have anything to release");
	}

	

	public Craft createCraft(Player player, CraftType craftType, int x, int y, int z, String name) {
		if (Central.getDebugManager().isDebugMode() == true)
			player.sendMessage("Attempting to create " + craftType.name
					+ "at coordinates " + Integer.toString(x) + ", "
					+ Integer.toString(y) + ", " + Integer.toString(z));

		Craft craft = Craft.getCraft(player);

		// release any old craft the player had
		if (craft != null) {
			releaseCraft(player, craft);
		}

		//float pRot = (float) Math.PI * player.getLocation().getYaw() / 180f;
		craft = new Craft(craftType, player, name, player.getLocation().getYaw());

		// auto-detect and create the craft
		if (!CraftBuilder.detect(craft, x, y, z)) {
			return null;
		}

		if(craft.engineBlocks.size() > 0)
			craft.timer = new MoveCraft_Timer(0, craft, "engineCheck", false);
		else {
			if(craft.type.requiresRails) {
				//craft.railMove();
			}
		}

		Craft.addCraft(craft);
		
		System.out.println("PILOT CREATE :" + player.getName() + " at (" + Integer.toString(craft.minX) + "," + Integer.toString(craft.minY) + "," + Integer.toString(craft.minZ) + ")");

		if(craft.type.listenItem == true)
			player.sendMessage(ChatColor.GRAY + "With an item in your hand, right-click in the direction you want to go.");
		if(craft.type.listenAnimation == true)
			player.sendMessage(ChatColor.GRAY + "Swing your arm in the direction you want to go.");
		if(craft.type.listenMovement == true)
			player.sendMessage(ChatColor.GRAY + "Move in the direction you want to go.");
		
		return craft;
	}

	public void dropItem(Block block) {		
		if(Central.configSetting("HungryHungryDrill").equalsIgnoreCase("true")){
			return;
                }

		int itemToDrop = BlocksInfo.getDropItem(block.getTypeId());
		int quantity = BlocksInfo.getDropQuantity(block.getTypeId());

		if(itemToDrop != -1 && quantity != 0){

			for(int i=0; i<quantity; i++){
				block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(itemToDrop, 1));
			}
		}
	}
}