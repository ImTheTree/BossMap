package me.darklolly0312.map.BossMap;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import me.darklolly0312.map.BossMap.CustomEntity.FriendlyDummy;
import me.darklolly0312.map.BossMap.CustomEntity.HostileDummy;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class Lobby implements Listener {
    static Plugin pl = Bukkit.getPluginManager().getPlugin("BossMap");
    static int timer1;
    static int timer2;
    static int timer3;
    static int timer4;
    static boolean playerOnWorld = false;
    public static HostileDummy hDummy;
    public static FriendlyDummy fDummy;
    
    public static void startTimers() {
        Lobby.timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobby.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
            	
                if (Bukkit.getWorld(init.worldName) != null) {
                    final World w = Bukkit.getWorld(init.worldName);
                    if (!w.getEntities().isEmpty()) {
                        for (final Entity e : w.getLivingEntities()) {
                        	if (e instanceof Player) {
                                playerOnWorld = true;
                            }
                        }
                        if (playerOnWorld) {
                        	if (hDummy == null && fDummy == null) { // only on first schedule?
                                Lobby.spawnHostileDummy();
                                Lobby.spawnFriendlyDummy();
                            } else {
                            	if (Lobby.hDummy.dead == true) {
                                    Lobby.spawnHostileDummy();
                            		}
                            		if (Lobby.fDummy.dead == true) {
                                    Lobby.spawnFriendlyDummy();
                                	}
                            }
                        } else {
                        	if (hDummy != null) {
                            	Bukkit.broadcastMessage("No players on world, removing hDummy");
                        		LivingEntity dummy = (LivingEntity) hDummy.getBukkitEntity();
                        		dummy.remove();
                        		hDummy = null;
                        	}
                        	if (fDummy != null) {
                            	Bukkit.broadcastMessage("No players on world, removing fDummy");
                        		LivingEntity dummy = (LivingEntity) fDummy.getBukkitEntity();
                        		dummy.remove();
                        		fDummy = null;
                        	}
                        }
                    }
                }
            }
        }, 10L, 10L);
        Lobby.timer2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobby.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getWorld(init.worldName) != null) {
                    final World w = Bukkit.getWorld(init.worldName);
                    if (!w.getEntities().isEmpty()) {
                        if (playerOnWorld) {
                            if (Lobby.hDummy != null) {
                                Lobby.hDummy.getBukkitEntity().teleport(init.lobby_hostileDummySpawn);
                            }
                            if (Lobby.fDummy != null) {
                                Lobby.fDummy.getBukkitEntity().teleport(init.lobby_friendlyDummySpawn);
                            }
                        }
                    }
                }
            }
        }, 5L, 5L);
        Lobby.timer3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobby.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getWorld(init.worldName) != null) {
                    final World w = Bukkit.getWorld(init.worldName);
                    if (!w.getEntities().isEmpty()) {
                        if (playerOnWorld && Lobby.fDummy != null) {
                            final LivingEntity e_fDummy = (LivingEntity)Lobby.fDummy.getBukkitEntity();
                            e_fDummy.damage(3.0);
    						e_fDummy.setNoDamageTicks(0);
                        }
                    }
                }
            }
        }, 20L, 20L);
    }
    
    @EventHandler
    public void onPlayerLeaveServer(final PlayerQuitEvent e) {
        World w = e.getPlayer().getWorld();
        ArrayList<Player> playersOnBossWorld = null;
        if (w.getPlayers() != null) {
            playersOnBossWorld = (ArrayList<Player>) w.getPlayers();
        }
        if (playersOnBossWorld != null) {
        	if (!playersOnBossWorld.isEmpty()) {
        		for (int i = 0; i < playersOnBossWorld.toArray().length; i++) {
					playersOnBossWorld.remove(i);
                	if (playersOnBossWorld.isEmpty()) {
                    	System.out.println("Last player from Bossworld left server, playerOnWorld = false");
                    	playerOnWorld = false;
                	}
				}
        	}
        }
    }
    
    @EventHandler
    public void onPlayerLeaveBossWorld(final PlayerChangedWorldEvent e) {
        World w = e.getFrom();
        if (w.getName().equals(init.worldName)) {
        	playerOnWorld = false;
        }
    }
    
    @EventHandler
    public void onDummyDamage(final EntityDamageEvent e) {
        if (e.getEntity() == Lobby.hDummy.getBukkitEntity()) {
            final LivingEntity dummy = (LivingEntity)Lobby.hDummy.getBukkitEntity();
            double newHealth = Methods.roundedDouble(dummy.getHealth() - e.getDamage());
            dummy.setCustomName("§cFeindliche Kampfattrappe [§2§lHP: §2" + newHealth + "/100§a]");
        }
        if (e.getEntity() == Lobby.fDummy.getBukkitEntity()) {
            final LivingEntity dummy = (LivingEntity)Lobby.fDummy.getBukkitEntity();
            double newHealth = Methods.roundedDouble(dummy.getHealth() - e.getDamage());
			dummy.setNoDamageTicks(0);
            dummy.setCustomName("§aFreundliche Kampfattrappe [§2§lHP: §2" + newHealth + "/100§a]");
        }
        
    }
    
    @EventHandler
    public void onDummyDeath(final EntityDeathEvent e) {
        if (e.getEntity() == Lobby.hDummy.getBukkitEntity()) {
            LivingEntity dummy = (LivingEntity)Lobby.hDummy.getBukkitEntity();
            dummy.remove();
            dummy = null;
        }
        if (e.getEntity() == Lobby.fDummy.getBukkitEntity()) {
            LivingEntity dummy = (LivingEntity)Lobby.fDummy.getBukkitEntity();
            dummy.remove();
            dummy = null;
        }
    }
    
    public static void spawnHostileDummy() {
        final HostileDummy entity = new HostileDummy(init.lobby_hostileDummySpawn);
        final WorldServer world = ((CraftWorld)Bukkit.getWorld(init.worldName)).getHandle();
        world.addEntity((net.minecraft.server.v1_16_R3.Entity)entity);
        Lobby.hDummy = entity;

    }
    
    public static void spawnFriendlyDummy() {
        final FriendlyDummy entity = new FriendlyDummy(init.lobby_friendlyDummySpawn);
        final WorldServer world = ((CraftWorld)Bukkit.getWorld(init.worldName)).getHandle();
        world.addEntity((net.minecraft.server.v1_16_R3.Entity)entity);
        Lobby.fDummy = entity;

    }
    
    public static void stopTimers() {
        Bukkit.getScheduler().cancelTask(Lobby.timer1);
        Bukkit.getScheduler().cancelTask(Lobby.timer2);
        Bukkit.getScheduler().cancelTask(Lobby.timer3);
        Bukkit.getScheduler().cancelTask(Lobby.timer4);
    }
}
