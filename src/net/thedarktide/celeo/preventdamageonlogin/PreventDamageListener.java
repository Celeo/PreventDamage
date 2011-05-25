/*
 * PreventDamageOnLogin
 * Copyright (C) 2010 Celeo <celeodor at gmail dot com>
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