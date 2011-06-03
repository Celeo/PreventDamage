/*
 * PreventDamageOnLogin
 * Copyright (C) 2011 Celeo <celeodor at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

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
	
	public DamageListener(PreventDamage instance){
		plugin = instance;
	}
	
	public void onEntityDamage(EntityDamageEvent event){
		if(event instanceof EntityDamageByEntityEvent){
			attacker = ((EntityDamageByEntityEvent)event).getDamager();
			defender = event.getEntity();
			try
			{
				if(Util.timeMap.get(attacker) >= System.currentTimeMillis())
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
			catch(NullPointerException npe)
			{
				
			}
		}
	}
	
}