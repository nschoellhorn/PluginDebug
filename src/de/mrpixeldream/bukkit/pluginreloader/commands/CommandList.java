package de.mrpixeldream.bukkit.pluginreloader.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CommandList extends CommandAbstract
{
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if (checkPerms(sender))
		{
			sender.sendMessage(ChatColor.GREEN + "[]-- Compatible Plugins --[]");
			for (Plugin now : Bukkit.getPluginManager().getPlugins())
			{
				sender.sendMessage(ChatColor.GOLD + "- " + now.getName() + " v"
						+ now.getDescription().getVersion());
			}
			sender.sendMessage(ChatColor.GREEN + "[]------------------------[]");
		}
		else
		{
			sender.sendMessage(PREFIX + ChatColor.RED + "You don't have permission!");
		}
	}

	@Override
	protected boolean checkPerms(CommandSender sender)
	{
		return sender.hasPermission("plr.list");
	}

	@Override
	protected boolean checkArgs(String[] args)
	{
		return args.length == 0;
	}
}
