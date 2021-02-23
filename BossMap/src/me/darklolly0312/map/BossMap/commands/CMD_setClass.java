package me.darklolly0312.map.BossMap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darklolly0312.map.BossMap.init;

public class CMD_setClass implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (args.length == 0) {
				wrongCommand(p);
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("berserker")) {
					
					p.sendMessage("§6Du bist nun Berserker!");
					init.playerClasses.put(p, "Berserker");
					
				} else
				if (args[0].equalsIgnoreCase("beschützer")) {
					
					p.sendMessage("§6Du bist nun Beschützer!");
					init.playerClasses.put(p, "Beschützer");
					
				} else
				if (args[0].equalsIgnoreCase("mönch")) {
					
					p.sendMessage("§6Du bist nun Mönch!");
					init.playerClasses.put(p, "Mönch");
					
				} else
				if (args[0].equalsIgnoreCase("schütze")) {
					
					p.sendMessage("§6Du bist nun Schütze!");
					init.playerClasses.put(p, "Schütze");
					
				} else {
					p.sendMessage("§cDiese Klasse existiert nicht!");
				}
			}
			
			if (args.length >= 2) {
				wrongCommand(p);
			}
			
		} else {
			System.out.print("Dieser Befehl ist nur als Spieler nutzbar!");
		}
		return true;
	}

	private void wrongCommand(Player p) {
		p.sendMessage("§cFalscher Befehl, nutze '/setclass <Klassenname>' um eine Klasse zu wählen.");
	}
	
	
}
