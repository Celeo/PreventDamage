package net.thedarktide.celeo.preventdamage;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PreventDamage extends JavaPlugin {
	
	public LogListener logListener = new LogListener(this);
	public DamageListener entityListener = new DamageListener(this);
	
	@Override
	public void onDisable() {
		Util.log.info(Util.pre + "disabled");
		Util.saveAll();
	}

	@Override
	public void onEnable() {
		Util.log.info(Util.pre + "enabled");
		PluginManager mngr = getServer().getPluginManager();
		mngr.registerEvent(Event.Type.PLAYER_JOIN, this.logListener, Event.Priority.Normal, this);
		mngr.registerEvent(Event.Type.PLAYER_QUIT, this.logListener, Event.Priority.Normal, this);
		mngr.registerEvent(Event.Type.ENTITY_DAMAGE, this.entityListener, Event.Priority.Normal, this);
		Util.loadAll(this);
		Util.log.info(Util.pre + "time to delay set to: " + Util.timeToDelay + " milliseconds.");
	}
	
}