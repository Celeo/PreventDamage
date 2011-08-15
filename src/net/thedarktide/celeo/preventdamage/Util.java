package net.thedarktide.celeo.preventdamage;

import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import java.util.HashMap;
import java.util.logging.Logger;

public class Util {
	
	public static PreventDamage plugin;
	
	public Util(PreventDamage instance){
		plugin = instance;
	}
	
	public static void loadAll(PreventDamage plugin) {
		try
		{
			config = plugin.getConfiguration();
			blockIncoming = config.getBoolean("block.incoming", blockIncoming);
			blockOutgoing = config.getBoolean("block.outgoing", blockOutgoing);
			Integer i = 0;
			i = config.getInt("time", timeToDelay.intValue());
			timeToDelay = i.longValue();
			saveAll();
		}
		catch(Exception ex)
		{
			timeToDelay = 5000L;
			blockOutgoing = true;
			blockIncoming = true;
			saveAll();
		}
	}
	
	public static void saveAll() {
		Util.config.setProperty("time", timeToDelay.intValue());
		Util.config.setProperty("block.incoming", blockOutgoing);
		Util.config.setProperty("block.outgoing", blockOutgoing);
		config.save();
		log.info(pre + "settings saved");
	}
	
	public static HashMap<Player, Long> timeMap = new HashMap<Player, Long>(); 
	public static Configuration config;
	public static Logger log = Logger.getLogger("Minecraft");
	public static String pre = "[PreventDamage] ";
	public static Long timeToDelay = 5000L;
	public static boolean blockIncoming = true;
	public static boolean blockOutgoing = true;
	
}