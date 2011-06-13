package net.thedarktide.celeo.preventdamage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class DamageListener extends EntityListener {
	
	public final PreventDamage plugin;
	
	Entity attacker = null;
	Entity defender = null;
	
	public DamageListener(PreventDamage instance) {
		plugin = instance;
	}
	
	public void onEntityDamage(EntityDamageEvent event) {
		if(event instanceof EntityDamageByEntityEvent) {
			attacker = ((EntityDamageByEntityEvent)event).getDamager();
			defender = event.getEntity();
			if(Util.timeMap.containsKey(attacker))
			{
				if((Util.timeMap.get(attacker) >= System.currentTimeMillis()) && Util.blockIncoming == true)
				{
					event.setCancelled(true);
				}
				if(defender instanceof Player && Util.blockOutgoing == true)
				{
					if(Util.timeMap.get(defender) >= System.currentTimeMillis())
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
}