package net.thedarktide.celeo.preventdamage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogListener extends PlayerListener{
	
	PreventDamage plugin;
	
	public LogListener(PreventDamage instance) {
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Util.timeMap.put(player, System.currentTimeMillis() + Util.timeToDelay);
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(Util.timeMap.containsKey(player))
		{
			Util.timeMap.remove(player);
		}
	}
	
}