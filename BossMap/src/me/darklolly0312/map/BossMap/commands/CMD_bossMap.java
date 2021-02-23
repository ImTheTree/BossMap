package me.darklolly0312.map.BossMap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darklolly0312.map.BossMap.Methods;
import me.darklolly0312.map.BossMap.devMode;
import me.darklolly0312.map.BossMap.init;
import me.darklolly0312.map.BossMap.testing.test8;
import me.darklolly0312.map.BossMap.testing.test9;
import me.darklolly0312.map.BossMap.testing.test1;
import me.darklolly0312.map.BossMap.testing.test2;
import me.darklolly0312.map.BossMap.testing.test3;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CMD_bossMap implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (args.length == 0) {
				TextComponent msg = new TextComponent("/bm spawn");
				msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bm spawn"));
				msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klicken um '/bm spawn' auszuführen...").create()));
				p.sendMessage("TestMessage: " + msg.toPlainText());
				
				p.sendMessage("§6-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				p.sendMessage("§6Plugin/Map Author: §edarklolly0312");
				p.sendMessage("§6Map-Version: §e0.5a");
				p.sendMessage("§6Plugin-Version: §e0.1a");
				p.sendMessage("");
				p.sendMessage("§6Befehle");
				p.sendMessage(" §e/bm spawn §6- §eTeleportiert dich zum Spawn.");
				p.sendMessage(" §e/bm test <subcommand> §6- §eTestbefehle");
				p.sendMessage(" §e/bm reset §6- §eResets the Plugin");
				p.sendMessage(" §e/bm toggledev §6- §eToggles devMode");
				p.sendMessage(" §e/bm forcestart §6- §eForces Arena to start in devMode");
				p.sendMessage(" §e/bm test <subcommand> §6- §eTestbefehle");
				p.sendMessage(" §e/ §6- §e");
				p.sendMessage("§6-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			}
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("spawn")) {
					p.sendMessage("Du wurdest zum BossMap-Spawn teleportiert.");
					p.teleport(init.lobby_playerSpawn);
				}
				
				if (args[0].equalsIgnoreCase("toggledev")) { // --------------------------- DEV MODE ---
					if (!devMode.enabled) {
						devMode.enable();
						p.sendMessage("[BossMap] Developer Mode: " + devMode.enabled);
						System.out.println("[BossMap] Developer Mode: " + devMode.enabled);
					} else {
						devMode.disable();
						p.sendMessage("[BossMap] Developer Mode: " + devMode.enabled);
						System.out.println("[BossMap] Developer Mode: " + devMode.enabled);
					}
				}
				
				if (args[0].equalsIgnoreCase("reset")) {
					p.sendMessage("Resetting BossMap...");
					init.resetPlugin();
				}
				
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("forcestart")) {
					p.performCommand("bm toggledev");
					p.performCommand("setclass Berserker");
					Methods.join(p);
					p.sendMessage("Force started arena");
				}
			}
			
			if (args.length == 2) {           // --------------------------- TEST-Commands ---------------------------  \\
				if (args[0].equalsIgnoreCase("test")) {
					
					if (args[1].equalsIgnoreCase("1")) {
						test1.use(); // lasergrid
						p.sendMessage("test: " + args[1] + " - lasergrid");
					}
					
					if (args[1].equalsIgnoreCase("2")) {
						test2.use();
						p.sendMessage("test: " + args[1] + " - laster from mid to players");
					}
					
					if (args[1].equalsIgnoreCase("3")) {
						test3.use(p);
						p.sendMessage("test: " + args[1] + " - meteor (only one)");
					}
					
					if (args[1].equalsIgnoreCase("4")) {
						p.sendMessage("test: " + args[1] + "- §7Not implemented yet.");
						
					}
					
					if (args[1].equalsIgnoreCase("5")) {
						p.sendMessage("test: " + args[1] + "- §7Not implemented yet.");
						
					}
					
					if (args[1].equalsIgnoreCase("6")) {
						p.sendMessage("test: " + args[1] + "- §7Not implemented yet.");
						
					}
					
					if (args[1].equalsIgnoreCase("7")) {
						p.sendMessage("test: " + args[1] + "- §7Not implemented yet.");
						
					}
					
					if (args[1].equalsIgnoreCase("8")) {
						test8.use(p);
						p.sendMessage("test: " + args[1] + " - cannon shoot graphic example");
						
					}
					
					if (args[1].equalsIgnoreCase("9")) {
						test9.use(p);
						p.sendMessage("test: " + args[1] + " - example code");
						
					}
					// TEST-Commands end...
					// CUSTOM-test start

					if (args[1].equalsIgnoreCase("EXAMPLE")) {

						p.sendMessage("CUSTOM-test: " + args[1] + " - EXAMPLEDESCRIPTION");
					}
					
					
					
					
					
					/*
					if (args[1].equalsIgnoreCase("EXAMPLE")) {

						p.sendMessage("CUSTOM-test: " + args[1] + " - EXAMPLEDESCRIPTION");
					}
					*/
					
					// CUSTOM-test end...
					
				}
			}
			
			
		} else {
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("Plugin/Map Author: darklolly0312");
			System.out.println("Map-Version: 0.5a");
			System.out.println("Plugin-Version: 0.1a");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		}
		return true;
	}
	
	
}












/*
	if args == 1...
	
	if (args[0].equalsIgnoreCase("test1")) {
					Arena.spawnBoss();
					p.sendMessage("spawned.");
				}
				
				if (args[0].equalsIgnoreCase("test2")) {
					
					ParticleEffects.fungraphic(p.getLocation());
					
					p.sendMessage(String.valueOf(args[0]));
				}
				
				if (args[0].equalsIgnoreCase("test3")) {
					
					ParticleEffects.summonCircle(p.getLocation());
					
					p.sendMessage(String.valueOf(args[0]));
				}
				
				if (args[0].equalsIgnoreCase("test4")) {
					
					ParticleEffects.Teleport(p.getLocation(), 1);
					
					p.sendMessage(String.valueOf(args[0]));
				}
				
				if (args[0].equalsIgnoreCase("test5")) {
					
					ParticleEffects.test1(p);
					
					p.sendMessage(String.valueOf(args[0]));
				}
				
				if (args[0].equalsIgnoreCase("test6")) {
					
					NPC.createNPC(p.getLocation());
					
					p.sendMessage(String.valueOf(args[0]) + " NPC created");
				}

				if (args[0].equalsIgnoreCase("test7")) {
					
					rain.use();
					
					p.sendMessage(String.valueOf(args[0]) + " used rain!");
				}

				if (args[0].equalsIgnoreCase("test8")) {
					
					SQLGetter.createPlayer(p);
					
					p.sendMessage(String.valueOf(args[0]) + " force createPlayer SQL");
				}





*/
