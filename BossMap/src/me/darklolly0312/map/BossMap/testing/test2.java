package me.darklolly0312.map.BossMap.testing;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.LocationCalculator;
import me.darklolly0312.map.BossMap.init;

public class test2{
	static Plugin pl = Bukkit.getPluginManager().getPlugin("BossMap");
	static int timer;
	static Location loc;
	public static boolean enabled = false;
	
	public static void startTimer() { // laser from arenamid to entities (dev)
			timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
				@Override
				public void run() {
					if (enabled) {
					
						loc = init.arena_mid;
						if (loc.getWorld().getNearbyEntities(loc, 20, 20, 20) != null) {
							for (Entity e : loc.getWorld().getNearbyEntities(loc, 20, 20, 20)) {
								if (e instanceof LivingEntity) {
									LocationCalculator.drawLine(loc, e.getLocation().add(0.0,1.0,0.0), Particle.CRIT, 0.2, 0.05, 0.0);
								}
							}
						}
					
					}
				}
			}, 0, 1);
	}
	
	public static void stopTimer() { // TODO add in init
		Bukkit.getScheduler().cancelTask(timer);
	}

	public static void use() {
		if (enabled) {
			enabled = false;
		} else {
			enabled = true;
		}
		
	}
	
	
	

}
