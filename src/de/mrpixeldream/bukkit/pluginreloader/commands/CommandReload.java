package de.mrpixeldream.bukkit.pluginreloader.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import de.mrpixeldream.bukkit.pluginreloader.PluginReloader;

public class CommandReload extends CommandAbstract
{
	PluginReloader main;
	
	public CommandReload(PluginReloader reloader)
	{
		this.main = reloader;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if (checkPerms(sender))
		{
			if (checkArgs(args))
			{
				String command = args[0];
				String plugin_name = args[1];
				Plugin plugin;
				
				command = checkCommand(command);
				
				if (command == null)
				{
					sender.sendMessage(PREFIX + ChatColor.GOLD + "Unknown command! Type /plr to list the commands!");
				}
				else if (command.equalsIgnoreCase("reload"))
				{
					plugin = main.getServer().getPluginManager().getPlugin(plugin_name);
					
					if (plugin != null)
					{
						sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Reloading " + plugin_name + "...");
						main.getServer().getPluginManager().disablePlugin(plugin);
						main.getServer().getPluginManager().enablePlugin(plugin);
						sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Successfully reloaded " + plugin_name + "!");
					}
					else
					{
						sender.sendMessage(PREFIX + ChatColor.RED + "Can't find this plugin! Type /plist to see compatible plugins!");
					}
				}
				else if (command.equalsIgnoreCase("disable"))
				{
					plugin = main.getServer().getPluginManager().getPlugin(plugin_name);
					
					if (plugin != null)
					{
						if (main.getServer().getPluginManager().getPlugin("PluginReloader") != plugin)
						{
							if (!plugin.isEnabled())
							{
								sender.sendMessage(PREFIX + ChatColor.RED + "This plugin is already disabled!");
							}
							else
							{
								sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Disabling " + plugin_name + "...");
								main.getServer().getPluginManager().disablePlugin(plugin);
								sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Successfully disabled " + plugin_name + "!");
							}
						}
						else
						{
							sender.sendMessage(PREFIX + ChatColor.RED + "You can't disable THIS plugin with itself!");
						}
					}
					else
					{
						sender.sendMessage(PREFIX + ChatColor.RED + "Can't find this plugin! Type /plist to see compatible plugins!");
					}
				}
				else if (command.equalsIgnoreCase("enable"))
				{
					plugin = main.getServer().getPluginManager().getPlugin(plugin_name);
					
					if (plugin != null)
					{
						if (main.getServer().getPluginManager().getPlugin("PluginReloader") != plugin)
						{
							if (plugin.isEnabled())
							{
								sender.sendMessage(PREFIX + ChatColor.RED + "This plugin is already enabled!");
							}
							else
							{
								sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Enabling " + plugin_name + "...");
								main.getServer().getPluginManager().disablePlugin(plugin);
								sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "Successfully enabled " + plugin_name + "!");
							}
						}
						else
						{
							sender.sendMessage(PREFIX + ChatColor.RED + "You can't enable THIS plugin with itself! By the way: if you see this message, the plugin IS enabled ;)");
						}
					}
					else
					{
						sender.sendMessage(PREFIX + ChatColor.RED + "Can't find this plugin! Type /plist to see compatible plugins!");
					}
				}
				else if (command.equalsIgnoreCase("debug"))
				{
					plugin = main.getServer().getPluginManager().getPlugin(plugin_name);
					
					if (plugin != null)
					{
						PluginDescriptionFile desc = plugin.getDescription();
						
						sender.sendMessage(PREFIX + ChatColor.BLUE + "[]--- Debug Information: ---[]");
						sender.sendMessage(PREFIX + ChatColor.YELLOW + "Total RAM: " + Runtime.getRuntime().totalMemory() / 1024L / 1024L + " MB");
						sender.sendMessage(PREFIX + ChatColor.YELLOW + "Free RAM: " + Runtime.getRuntime().freeMemory() / 1024L / 1024L + " MB");
						
						long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
						
						sender.sendMessage(PREFIX + ChatColor.YELLOW + "Used RAM: " + used / 1024L / 1024L);
						sender.sendMessage(PREFIX + "");
						sender.sendMessage(PREFIX + ChatColor.GOLD
								+ "Plugin name: " + desc.getName());
						sender.sendMessage(PREFIX + ChatColor.GOLD + "Plugin version: " + desc.getVersion());
						sender.sendMessage(PREFIX + ChatColor.GOLD
								+ "Main class: " + desc.getMain());
						sender.sendMessage(PREFIX + ChatColor.GOLD
								+ "Dependent on: " + desc.getDepend());
						sender.sendMessage(PREFIX + "" );
						sender.sendMessage(PREFIX + ChatColor.LIGHT_PURPLE + "Server version: " +  Bukkit.getVersion());
					}
					else
					{
						sender.sendMessage(PREFIX + ChatColor.RED + "Can't find this plugin! Type /plist to see compatible plugins!");
					}
				}
			}
			else
			{
				if (args.length == 0)
				{
					sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "[]--- Commands of /plr ---[]");
					sender.sendMessage(PREFIX + ChatColor.DARK_GRAY + "reload = Reloads the specified plugin.");
					sender.sendMessage(PREFIX + ChatColor.DARK_GRAY + "disable = Disables the plugin until next server reload/restart.");
					sender.sendMessage(PREFIX + ChatColor.DARK_GRAY + "enable = Enables the plugin if it isn't enabled yet.");
					sender.sendMessage(PREFIX + ChatColor.DARK_GRAY + "debug = Shows debug information about the plugin.");
					sender.sendMessage("");
					sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + "[]--- /plist ---[]");
					sender.sendMessage(PREFIX + ChatColor.DARK_GRAY + "Shows all compatible plugins.");
					
					return;
				}
				
				sender.sendMessage(PREFIX + ChatColor.RED + "Usage: /plr <command> <plugin>");
			}
		}
		else
		{
			sender.sendMessage(PREFIX + ChatColor.RED + "You don't have permission!");
		}
	}

	@Override
	protected boolean checkPerms(CommandSender sender)
	{
		return sender.hasPermission("plr.control");
	}

	@Override
	protected boolean checkArgs(String[] args)
	{
		return args.length == 2;
	}
	
	private String checkCommand(String cmd)
	{
		if (cmd.equalsIgnoreCase("reload"))
		{
			return cmd;
		}
		else if (cmd.equalsIgnoreCase("disable"))
		{
			return cmd;
		}
		else if (cmd.equalsIgnoreCase("enable"))
		{
			return cmd;
		}
		else if (cmd.equalsIgnoreCase("debug"))
		{
			return cmd;
		}
		
		return null;
	}
}