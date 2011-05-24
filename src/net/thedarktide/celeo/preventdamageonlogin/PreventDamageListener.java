package net.thedarktide.celeo.preventdamageonlogin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Date;

public class PreventDamageListener extends PlayerListener{
	
	public final PreventDamage plugin;
	
	public HashMap<Player, Long> timeMap = new HashMap<Player, Long>(); 
	
	public PreventDamageListener(PreventDamage instance){
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerEvent event){
		Player player = event.getPlayer();
		if(PreventDamage.isPreventing == true)
		{
			
		}
	}
	
	public void onEntityDamage(EntityDamageEvent event) {
		Player defender = (Player)event.getEntity();
		if(PreventDamage.isPreventing == true)
		{
			
		}
	}
	
}