package net.thedarktide.celeo.preventdamage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class LoginListener extends PlayerListener {
	
	public final PreventDamage plugin;
	
	public LoginListener(PreventDamage instance){
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerEvent event){
		Player player = event.getPlayer();
		Util.timeMap.put(player, System.currentTimeMillis()+Util.timeToDelay);
	}
	
}
