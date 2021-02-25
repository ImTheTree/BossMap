package me.darklolly0312.map.BossMap;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.classes.ClassHandler;

public class ArenaMethods {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    
    public static void reset() {
        init.joinedPlayers.clear();
        ClassHandler.playerClasses.clear();
        init.isActive = false;
    }
    
    public static void leave(final Player p) {
        init.joinedPlayers.remove(p);
        ClassHandler.playerClasses.remove(p);
        p.teleport(init.lobby_playerSpawn);
        p.sendMessage("§7Du hast die Arena verlassen.");
        if (init.joinedPlayers.toArray().length == 0) {
            reset();
        }
    }
    
    public static void join(final Player p) {
        if (ClassHandler.playerClasses.containsKey(p)) {
            if (devMode.enabled) {
                Bukkit.broadcastMessage("DEV1_ devmode forcestart arena");
                init.joinedPlayers.add(p);
                p.teleport(init.arena_playerPos_3);
                Arena.startEncounter();
            }
            else if (init.joinedPlayers.toArray().length != init.maxPlayers) {
                if (init.joinedPlayers.toArray().length == 0) {
                    p.sendMessage("§6§lBetrete Arena...");
                    if (!init.joinedPlayers.contains(p)) {
                        init.joinedPlayers.add(p);
                        p.sendMessage("§7Du hast den Warteraum betreten. Die Arena started sobald ein weiterer Spieler beigetreten ist.");
                        p.teleport(init.waitRoom_playerSpawn);
                    }
                    else {
                        p.sendMessage("§cDu bist bereits der Arena beigetreten!");
                    }
                }
                if (init.joinedPlayers.toArray().length == 1) {
                    if (!init.joinedPlayers.contains(p)) {
                        init.joinedPlayers.add(p);
                        p.sendMessage("§6§lBetrete Arena...");
                        p.teleport(init.waitRoom_playerSpawn);
                        for (final Player tp : init.joinedPlayers) {
                            tp.sendMessage("§aDie Arena starte in:");
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    tp.sendMessage("§a5...");
                                }
                            }, 20L);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    tp.sendMessage("§a4...");
                                }
                            }, 40L);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    tp.sendMessage("§a3...");
                                }
                            }, 60L);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    tp.sendMessage("§a2...");
                                }
                            }, 80L);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    tp.sendMessage("§a1...");
                                }
                            }, 100L);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(ArenaMethods.pl, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    ArenaMethods.teleportJoinedPlayers();
                                    Arena.startEncounter();
                                }
                            }, 120L);
                        }
                    }
                    else {
                        p.sendMessage("§cDu bist bereits der Arena beigetreten!");
                    }
                }
            }
            else {
                p.sendMessage("§cDie Arena ist bereits voll!");
            }
        }
        else {
            p.sendMessage("§cDu musst zuerst eine Klasse w\u00e4hlen um die Arena betreten zu k\u00f6nnen!");
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
