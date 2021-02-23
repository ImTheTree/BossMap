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
					
					p.sendMessage("�6Du bist nun Berserker!");
					init.playerClasses.put(p, "Berserker");
					
				} else
				if (args[0].equalsIgnoreCase("besch�tzer")) {
					
					p.sendMessage("�6Du bist nun Besch�tzer!");
					init.playerClasses.put(p, "Besch�tzer");
					
				} else
				if (args[0].equalsIgnoreCase("m�nch")) {
					
					p.sendMessage("�6Du bist nun M�nch!");
					init.playerClasses.put(p, "M�nch");
					
				} else
				if (args[0].equalsIgnoreCase("sch�tze")) {
					
					p.sendMessage("�6Du bist nun Sch�tze!");
					init.playerClasses.put(p, "Sch�tze");
					
				} else {
					p.sendMessage("�cDiese Klasse existiert nicht!");
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
		p.sendMessage("�cFalscher Befehl, nutze '/setclass <Klassenname>' um eine Klasse zu w�hlen.");
	}
	
	
}
