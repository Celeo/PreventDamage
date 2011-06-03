/*
 * PreventDamageOnLogin
 * Copyright (C) 201` Celeo <celeodor at gmail dot com>
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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class PreventDamage extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	public LoginListener playerListener = new LoginListener(this);
	public DamageListener entityListener = new DamageListener(this);
	
	@Override
	public void onDisable() {
		log.info("[Damage Prevention] <disabled>");
		Util.config.setProperty("time", Util.timeToDelay.intValue());
		Util.config.setProperty("block.incoming", Util.blockOutgoing);
		Util.config.setProperty("block.outgoing", Util.blockOutgoing);
		Util.config.save();
	}

	@Override
	public void onEnable() {
		log.info("[Damage Prevention] <enabled>");
		PluginManager mngr = getServer().getPluginManager();
		mngr.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener, Event.Priority.Normal, this);
		mngr.registerEvent(Event.Type.ENTITY_DAMAGE, this.entityListener, Event.Priority.Normal, this);

		Util.load(this);
		Util.blockIncoming = Util.config.getBoolean("block.incoming", Util.blockIncoming);
		Util.blockOutgoing = Util.config.getBoolean("block.outgoing", Util.blockOutgoing);
		Integer i = 0;
		i = Util.config.getInt("time", Util.timeToDelay.intValue());
		Util.timeToDelay = i.longValue();
		if(Util.timeToDelay == null || Util.timeToDelay == 0)
		{
			Util.timeToDelay = 5000L;
			Util.config.setProperty("time", 5000);
		}
		log.info("[PreventDamage] time to delay set to: " + Util.timeToDelay + " milliseconds.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("pd"))
		{
			log.info("Time to delay is: " + Util.timeToDelay);
		}
		return true;
	}
	
}