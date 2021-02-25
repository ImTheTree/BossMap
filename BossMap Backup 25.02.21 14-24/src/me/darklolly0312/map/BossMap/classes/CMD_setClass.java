package me.darklolly0312.map.BossMap.classes;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CMD_setClass implements CommandExecutor {

	@Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                this.wrongCommand(p);
            }
            if (args.length == 1) {
            	if (args[0].equalsIgnoreCase("berserker")
            		|| args[0].equalsIgnoreCase("beschützer")
            		|| args[0].equalsIgnoreCase("mönch")
            		|| args[0].equalsIgnoreCase("schütze")) {
            		ClassHandler.setPlayerClass(p, args[0]);
                    p.sendMessage("§6Du bist nun " + args[0] + "!");
            	}
                else {
                    p.sendMessage("§cDiese Klasse existiert nicht!");
                }
            }
            if (args.length >= 2) {
                this.wrongCommand(p);
            }
        }
        else {
            System.out.print("Dieser Befehl ist nur als Spieler nutzbar!");
        }
        return true;
    }
    
    private void wrongCommand(final Player p) {
        p.sendMessage("§cFalscher Befehl, nutze '/setclass <Klassenname>' um eine Klasse zu w\u00e4hlen.");
    }
}
