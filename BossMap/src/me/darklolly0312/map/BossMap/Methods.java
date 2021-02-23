package me.darklolly0312.map.BossMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Methods {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	
	
	
	public static void reset() {
		init.joinedPlayers.clear();
		init.playerClasses.clear();
		init.isActive = false;
	}
	
	
	public static void leave (Player p) {
		init.joinedPlayers.remove(p);
		init.playerClasses.remove(p);
		p.teleport(init.lobby_playerSpawn);
		p.sendMessage("§7Du hast die Arena verlassen.");
		if (init.joinedPlayers.toArray().length == 0) {
			Methods.reset();
		}
	}
	
	public static void join(Player p) {
		
		if (init.playerClasses.containsKey(p)) {
			
			if (devMode.enabled) { // ----------------------- DEV MODE FORCESTART ---
				Bukkit.broadcastMessage("DEV1_ devmode forcestart arena");
				p.teleport(init.arena_playerPos_3);
				Arena.startEncounter();
			} else
			
			if (init.joinedPlayers.toArray().length != init.maxPlayers) {
				if (init.joinedPlayers.toArray().length == 0) {
					p.sendMessage("§6§lBetrete Arena...");
					if (!init.joinedPlayers.contains(p)) {
						init.joinedPlayers.add(p);
						p.sendMessage("§7Du hast den Warteraum betreten. Die Arena started sobald ein weiterer Spieler beigetreten ist.");

						p.teleport(init.waitRoom_playerSpawn);
						
					} else {
						p.sendMessage("§cDu bist bereits der Arena beigetreten!");
					}
				}

				if (init.joinedPlayers.toArray().length == 1) {
					if (!init.joinedPlayers.contains(p)) {
						init.joinedPlayers.add(p);
						p.sendMessage("§6§lBetrete Arena...");
						
						p.teleport(init.waitRoom_playerSpawn);
						
						for (Player tp : init.joinedPlayers) {
							tp.sendMessage("§aDie Arena starte in:");
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									tp.sendMessage("§a5...");
								}
							}, 20);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									tp.sendMessage("§a4...");
								}
							}, 40);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									tp.sendMessage("§a3...");
								}
							}, 60);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									tp.sendMessage("§a2...");
								}
							}, 80);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									tp.sendMessage("§a1...");
								}
							}, 100);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {@Override
								public void run() {
									teleportJoinedPlayers();
									Arena.startEncounter();
									
								}
							}, 120);
							
						}
					} else {
						p.sendMessage("§cDu bist bereits der Arena beigetreten!");
					}
				}
				
			} else {
				p.sendMessage("§cDie Arena ist bereits voll!");
			}
		} else {
			p.sendMessage("§cDu musst zuerst eine Klasse wählen um die Arena betreten zu können!");
		}
		
		
		
	}


	protected static void teleportJoinedPlayers() {
		if (init.joinedPlayers.get(0) != null) {
			init.joinedPlayers.get(0).teleport(init.arena_playerPos_1);
		}
		if (init.joinedPlayers.get(1) != null) {
			init.joinedPlayers.get(1).teleport(init.arena_playerPos_2);
		}
		if (init.joinedPlayers.get(2) != null) {
			init.joinedPlayers.get(2).teleport(init.arena_playerPos_3);
		}
		if (init.joinedPlayers.get(3) != null) {
			init.joinedPlayers.get(3).teleport(init.arena_playerPos_4);
		}
		
	}
	
	
	
}
