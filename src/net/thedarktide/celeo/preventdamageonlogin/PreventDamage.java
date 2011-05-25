package net.thedarktide.celeo.preventdamageonlogin;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.util.logging.Logger;

public class PreventDamage extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	public static boolean isPreventing = true;
	public final PreventDamageListener listener = new PreventDamageListener(this);
	protected static PermissionHandler Permissions = null;
	
	@Override
	public void onDisable() {
		log.info("[Damage Prevention on Player Login] <disabled>");
	}

	@Override
	public void onEnable() {
		log.info("[Damage Prevention on Player Login] <enabled>");
		setupPermissions();
		PluginManager mngr = getServer().getPluginManager();
		mngr.registerEvent(Event.Type.PLAYER_JOIN, this.listener, Event.Priority.Normal, this);
		mngr.registerEvent(Event.Type.ENTITY_DAMAGE, this.listener, Event.Priority.Normal, this);
	}
	
	private void printOut(String str){
		log.info(str);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = null;
		String senderName = null;
		
		if(sender instanceof Player)
		{
			player = (Player)sender;
			senderName = player.getName();
			
			if(commandLabel.equalsIgnoreCase("preventdamage"))
			{
				if(args.length >= 0)
				{
					
					if(args[0].equalsIgnoreCase("-set") && Permissions.has(player, "preventdamage.setdelay"))
					{
						try
						{
							PreventDamageListener.timeToDelay = Long.parseLong(args[1]);
						}
						catch (NumberFormatException nfe)
						{
							player.sendMessage(ChatColor.GRAY + "You need to use a number. >.>");
						}
						player.sendMessage(ChatColor.GRAY + "Delay set to " + PreventDamageListener.timeToDelay.toString());
					}
					if(args[0].equalsIgnoreCase("-toggle") && Permissions.has(player, "preventdamage.toggle"))
					{
						if(args[1].equalsIgnoreCase("on"))
						{
							isPreventing = true;
							displayState(player);
						}
						else if(args[1].equalsIgnoreCase("off"))
						{
							isPreventing = false;
							displayState(player);
						}
					}
					
				}
			}
			
		}
		return true;
	}
	
	public void displayState(Player player){
		player.sendMessage(ChatColor.RED + "Damage Prevention " + ChatColor.GRAY + "toggled to " + isPreventing);
	}
	
	public void setupPermissions() {
	    Plugin test = getServer().getPluginManager().getPlugin("Permissions");

	    if (Permissions == null)
	      if (test != null)
	      {
	        getServer().getPluginManager().enablePlugin(test);
	        Permissions = ((Permissions)test).getHandler();
	      } 
	      else
	      {
	        log.info("[Damage Prevention on Player Login] version" + "1.0" + "requires Permissions, disabling...");
	        getServer().getPluginManager().disablePlugin(this);
	      }
	  }

}