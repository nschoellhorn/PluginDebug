package de.mrpixeldream.bukkit.pluginreloader.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class CommandAbstract
{
	protected final String	PREFIX	= ChatColor.DARK_AQUA + "[PluginDebug] ";
	protected final String	CONSOLE	= "[PluginDebug] ";
	
	public abstract void execute(CommandSender sender, String[] args);
	
	protected abstract boolean checkPerms(CommandSender sender);
	protected abstract boolean checkArgs(String[] args);
}