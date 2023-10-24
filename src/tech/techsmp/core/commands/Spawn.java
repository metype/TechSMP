package tech.techsmp.core.commands;
import tech.techsmp.core.Join.PlayerPreJoin;
import tech.techsmp.core.cosmetic.Chat;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.ConfigMessage;
import utils.Teleporter;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Teleporter.teleport(p, Bukkit.getWorld("world").getSpawnLocation());

		//p.teleport(new Location(Bukkit.getWorld("world"), -95, 85, 0));
		p.sendMessage(ConfigMessage.getMessage("SPAWN_TP_TP_SPAWN", new String[]{" "}));
		return true;

	}
}
