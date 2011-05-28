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

import java.util.HashMap;

public class Util{
	
	public static HashMap<Player, Long> timeMap = new HashMap<Player, Long>(); 
	
	public static Long timeToDelay = 10000L;
	
	public static boolean isDebugging = false;
	
	public final PreventDamage plugin;
	
	public Util(PreventDamage instance){
		plugin = instance;
	}
	
}