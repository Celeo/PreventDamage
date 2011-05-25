package net.thedarktide.celeo.preventdamageonlogin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

import java.util.HashMap;

public class PreventDamageListener extends PlayerListener{
	
	public final PreventDamage plugin;
	
	public HashMap<Player, Long> timeMap = new HashMap<Player, Long>(); 
	public static Long timeToDelay = (long) 3000;
	
	public PreventDamageListener(PreventDamage instance){
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerEvent event){
		Player player = event.getPlayer();
		if(PreventDamage.isPreventing == true)
		{
			timeMap.put(player, System.currentTimeMillis()*1000L*timeToDelay);
		}
	}
	
	public void onEntityDamage(EntityDamageEvent event) {
		Player attacker = (Player)event.getEntity();
		if(PreventDamage.isPreventing == true)
		{
			if(timeMap.get(attacker) <= timeToDelay)
			{
				event.setCancelled(true);
			}
		}
	}
	
}