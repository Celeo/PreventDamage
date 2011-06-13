package net.thedarktide.celeo.preventdamage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoutListener extends PlayerListener {
	
	public final PreventDamage plugin;
	
	public LogoutListener(PreventDamage instance) {
		plugin = instance;
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(Util.timeMap.containsKey(player))
		{
			Util.timeMap.remove(player);
		}
	}
	
}
