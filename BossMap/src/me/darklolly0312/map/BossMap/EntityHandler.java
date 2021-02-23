package me.darklolly0312.map.BossMap;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public class EntityHandler {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");

	public static List<Entity> getEntitiesByLocation(Location loc, float r) { // DEFAULT
	    List<Entity> ent = new ArrayList<Entity>();
	    for (Entity e : loc.getWorld().getEntities()) {
	      if (e.getLocation().distanceSquared(loc) <= r) {
	        ent.add(e);
	      }
	    }
	    return ent;
	  }
	
	// um entitys an für z.b. Missile-Hit zu kriegen, falls große hitbox
	public static List<Entity> getEntitiesByFixedLocation(Location loc, float r) {                   
	    List<Entity> ent = new ArrayList<Entity>();
	    for (Entity e : loc.getWorld().getEntities()) {
	    	if (e.getLocation().distanceSquared(loc) <= r) {
	    		ent.add(e);
	    	}
	    }
	    return ent;
	}
}



// (Particle arg0, Location arg1, int arg2, 
//double arg3, double arg4, double arg5, double arg6)
// + double double double?
//




























