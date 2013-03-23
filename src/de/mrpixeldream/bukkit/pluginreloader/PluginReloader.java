package de.mrpixeldream.bukkit.pluginreloader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.mrpixeldream.bukkit.pluginreloader.commands.CommandList;
import de.mrpixeldream.bukkit.pluginreloader.commands.CommandReload;

public class PluginReloader extends JavaPlugin
{
	private final String	CONSOLE	= "[PluginDebug] ";
	
	private CommandReload cmd_reload;
	private CommandList		cmd_list;
	
	@Override
	public void onEnable()
	{
		System.out.println(CONSOLE + "Enabling PluginDebug...");
		System.out.println(CONSOLE + "Registering commands...");
		
		cmd_reload = new CommandReload(this);
		cmd_list = new CommandList();
		
		System.out.println(CONSOLE + "Successfully registered commands!");
		System.out.println(CONSOLE + "Thanks for using PluginDebug!");
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (label.equalsIgnoreCase("plr"))
		{
			cmd_reload.execute(sender, args);
		}
		if (label.equalsIgnoreCase("plist"))
		{
			cmd_list.execute(sender, args);
		}
		
		return true;
	}
}