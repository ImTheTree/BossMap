package me.darklolly0312.map.BossMap.testing;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.Arena;
import me.darklolly0312.map.BossMap.init;

public class rain {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	static int timer1;
	static int timer2;

	public static void use() {
		Bukkit.broadcastMessage("rain activated"); // temp message

		ArrayList<Location> locs = new ArrayList<Location>();
		timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			int count = 0;
			@Override
			public void run() {
				if (count < 5*20) {
					Location tl = new Location(Bukkit.getWorld(init.worldName), Arena.boss.locX(), Arena.boss.locY(), Arena.boss.locZ());
					locs.add(tl);
				}
				if (count >= 100) {
					Bukkit.getScheduler().cancelTask(timer1);
				}
				count = count + 10;
			}
		}, 10, 10);
		
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {

			@Override
			public void run() {
				for (Location l : locs) {
					Location upperLoc = l.add(0.0, 10.0, 0.0);
					
					timer2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
						@Override
						public void run() {
							
							if (upperLoc.getY() >= l.getY()) {
								upperLoc.setY(upperLoc.getY() - 0.3);
							}

							if (upperLoc.getY() <= l.getY()) { // do something on ground collide
								Bukkit.getScheduler().cancelTask(timer2);
							}

							// spawnParticle, 
							for (Location tl : locs) {
								tl.getWorld().spawnParticle(Particle.REDSTONE, tl.getX(), tl.getY(), tl.getZ(), 0, 0.5, 0.0, 0.5, new Particle.DustOptions(Color.RED,1));
								// ParticleEffects.rainIndicator(tl);
							}
							
						}
					}, 10, 10);
					
				}
				
			}
		}, 101);
		
		
		
		
		
		
		
		
		
		
		
	}

}




































