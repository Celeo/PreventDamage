package net.thedarktide.celeo.preventdamage;

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
		Util.saveAll();
	}

	@Override
	public void onEnable() {
		log.info("[Damage Prevention] <enabled>");
		PluginManager mngr = getServer().getPluginManager();
		mngr.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener, Event.Priority.Normal, this);
		mngr.registerEvent(Event.Type.ENTITY_DAMAGE, this.entityListener, Event.Priority.Normal, this);
		Util.loadAll(this);
		log.info("[PreventDamage] time to delay set to: " + Util.timeToDelay + " milliseconds.");
	}
	
}