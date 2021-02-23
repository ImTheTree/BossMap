package me.darklolly0312.map.BossMap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.CustomEntity.Boss;
import net.minecraft.server.v1_16_R3.WorldServer;

public class Arena {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	public static Boss boss;
	public static int scheduler1;
	public static int scheduler2;
	public static int scheduler3;
	public static int scheduler4;
	public static int scheduler5;
	public static boolean bossAbleToCast = true;
	
	public static void startEncounter() {
		init.isActive = true;

		/*if (devMode.enabled) {   // ------------------------------------------------------------ DEV MODE ---
			Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					if (!init.joinedPlayers.isEmpty()) {
						for (Player p : init.joinedPlayers) {
							if (p.getHealth() < p.getMaxHealth()) {
								p.setHealth(p.getHealth() + 1.0);
							}
						}
					}
				}
			}, 20, 20);
		}*/

		spawnBoss();
		
		SpellHandler.startRandomCasting();

		scheduler1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
				if (bossAbleToCast) {
					// disable "bossAbleToCast" variable to force casting a specific spell (like phases)
					double rng1 = Math.random()*100;
					if (rng1 >=80) {
						int id = new Random().nextInt(SpellHandler.spells + 1);
						Bukkit.broadcastMessage("DEV1_ castrandomspell ID=" + id);
						SpellHandler.cast(id);
						// get random spell, cast and make a cast-cooldown
						
						
						
						
						
					}
				}
			}
		}, 20, 20);
		
		
		
		
		
		
		
	}
	
	
	public static void spawnBoss() {
		Boss entity = new Boss(init.arena_bossSpawn);
		WorldServer world = ((CraftWorld) Bukkit.getWorld(init.worldName)).getHandle();
		world.addEntity(entity);
		boss = entity;
		for (Player p : init.joinedPlayers) {
			p.sendMessage(boss.getName() + " schreit: §oIhr wagt es MICH herauszufordern? Dafür werdet ihr mit eurem Leben bezahlen!");
		}

		ParticleEffects.summonCircle(init.arena_bossSpawn);
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {

			@Override
			public void run() {
				ParticleEffects.summonCircle(init.arena_bossSpawn);
				
			}
		}, 5);
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {

			@Override
			public void run() {
				ParticleEffects.summonCircle(init.arena_bossSpawn);
				
			}
		}, 10);
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {

			@Override
			public void run() {
				ParticleEffects.summonCircle(init.arena_bossSpawn);
				
			}
		}, 15);
	}
	
	
	
}




































