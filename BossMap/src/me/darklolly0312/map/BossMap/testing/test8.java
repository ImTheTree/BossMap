package me.darklolly0312.map.BossMap.testing;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class test8 {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");

	public static void use(Player p) {
		Location loc = p.getLocation();
		World w = p.getWorld();
		
		// Cannon shoot example
		
	    double radius = 0.4;
	    int n = 8;
	    for(int i = 0; i < 6; i++) {
	        double angle = 2 * Math.PI * i / n;
	        Location base = loc.clone().add(new Vector(radius * Math.cos(angle), 0, radius * Math.sin(angle)));
	        for(int j = 0; j <= 8; j++) {
	            w.playEffect(base, Effect.SMOKE, j);
	        }
	    }
		
	}

}
