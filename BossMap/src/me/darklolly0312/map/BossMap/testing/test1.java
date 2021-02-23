package me.darklolly0312.map.BossMap.testing;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.init;

public class test1 implements Listener{
	static Plugin pl = Bukkit.getPluginManager().getPlugin("BossMap");
	static int timer1;
	static int timer2;
	static Location sourceLoc;
	static Location loc;
	static ArrayList<Location> particleLocs = new ArrayList<Location>();
	static double minX;
	static double maxX;
	static String direction = "plus";
	static double maxRange = 36.0;
	static double maxLength = 36.0;
	
	public static void use() { // arena lasergrid code (TODO multiple lasers)
		stopTimers();
		particleLocs.clear();
		direction = "plus";
		sourceLoc = new Location(Bukkit.getWorld(init.worldName), 103.5, 16.1, -82.5);
		loc = sourceLoc;
		minX = sourceLoc.getX();
		maxX = sourceLoc.getX() + maxRange;
		particleLocs = new ArrayList<Location>();
		
		
		timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
		    	
		    	
		    	if (direction.equals("plus")) {
		    		//works
					loc.setX(loc.getX() + 0.5);
					particleLocs.add(loc);
					
					for (Location a : getLineLocsZ(loc)) {
						particleLocs.add(a);
					}
					
					if (loc.getX() >= maxX) {
						direction = "minus";
					}
		    	}
		    	
		    	if (direction.equals("minus")) {
		    		//works
					loc.setX(loc.getX() - 0.5);
					particleLocs.add(loc);
					
					for (Location a : getLineLocsZ(loc)) {
						particleLocs.add(a);
					}
					
					if (loc.getX() <= minX) {
						direction = "plus";
					}
		    	}

		    	
		    	
		    	
		    	loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 0.1, new Particle.DustOptions(Color.RED,1));
		    	//loc.getWorld().spawnParticle(Particle.CRIT_MAGIC, loc, 1, 0, 0, 0, 0.0001);
			}
		}, 2, 2);
		
		
		
		
		
		
	}
	
	
	protected static ArrayList<Location> getLineLocsZ(Location loc) {
		Location tile = new Location(Bukkit.getWorld(init.worldName), loc.getX(), loc.getY(), loc.getZ());
		ArrayList<Location> tiles = new ArrayList<Location>();
		double multiply = 3;
		int ti = (int) multiply;
		double math = 1.0/ti;
		for (int i = 0; i <= maxLength*ti; i++) {
			tile.setZ(tile.getZ()+math);
			tiles.add(tile);

			tile.getWorld().spawnParticle(Particle.REDSTONE, tile, 1, 0, 0, 0, 0.1, new Particle.DustOptions(Color.RED,1));
			
			if (!tile.getWorld().getNearbyEntities(tile, 0.2, 0.2, 0.2).isEmpty()) {
				for (Entity e : tile.getWorld().getNearbyEntities(tile, 0.2, 0.2, 0.2)) {
					if (e instanceof Player) {
						Player p = (Player) e;
						p.damage(6.0);
					}
				}
			}
			
			
		}
		return tiles;
	}


	public static void startTimers() {
		
	}
	
	public static void stopTimers() { // TODO add in init
		Bukkit.getScheduler().cancelTask(timer1);
		Bukkit.getScheduler().cancelTask(timer2);
	}
	
	
	

}
