package me.darklolly0312.map.BossMap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.bossSpells.napalm_bombs;

public class SpellHandler {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	private static int timer_castHandler;
	public static int spells = 1;
	public static boolean forceCast = false;

	public static void cast(int id) {
		
		if (!forceCast) {
			
			if (id == 0) {
				napalm_bombs.use(); // NAPALM BOMBS
				Arena.bossAbleToCast = false;
				Bukkit.broadcastMessage("§cNapalmbomben!");
			}
			if (id == 1) {
				//example1.use();
				Arena.bossAbleToCast = false;
			}
			if (id == 2) {
				//example1.use();
				Arena.bossAbleToCast = false;
			}
			if (id == 3) {
				//example1.use();
				Arena.bossAbleToCast = false;
			}
			if (id == 4) {
				//example1.use();
				Arena.bossAbleToCast = false;
			}
			if (id == 5) {
				//example1.use();
				Arena.bossAbleToCast = false;
			}
			
			// dev TEMP
			int cooldown = 10;
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				@Override
				public void run() {
					Arena.bossAbleToCast = true;				
				}
			}, cooldown*20);
			
		} else {
			Bukkit.broadcastMessage("cant cast spell(" + id + "), because forceCast is active!");
		}
		
	}
	
	public static void forceCast(int id) {
		forceCast = true;
		
		if (id == 100) {
			
		}
		if (id == 101) {
			
		}
		if (id == 102) {
			
		}
		if (id == 103) {
			
		}
		if (id == 104) {
			
		}
		if (id == 105) {
			
		}
		

		// dev TEMP
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			@Override
			public void run() {
				forceCast = false;
				Bukkit.broadcastMessage("boss can now cast random spells, deactivated forceCast");
			}
		}, 5*20);
	}

	public static void startRandomCasting() {
		timer_castHandler = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
				if (Arena.bossAbleToCast) {
					// disable "bossAbleToCast" variable to force casting a specific spell (like phases)
					
					double rng1 = Math.random()*100;
					if (rng1 >=80) {
						int id = new Random().nextInt(SpellHandler.spells + 1);
						cast(id);
					}
					
				}
			}
		}, 20, 20);
		
	}
	
	public static void stopRandomCasting() {
		Bukkit.getScheduler().cancelTask(timer_castHandler);;
	}
	
	
	
	
	
	
	
	
	
	
}





















































