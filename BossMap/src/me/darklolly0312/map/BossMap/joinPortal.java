package me.darklolly0312.map.BossMap;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class joinPortal implements Listener {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	public static ArrayList<Player> playerInPortal = new ArrayList<Player>();
	
	public static void startPortal() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
				
				for (Entity e : EntityHandler.getEntitiesByLocation(init.lobby_joinPortal, (float)1)) {
					if (e instanceof Player) {
						Player p = (Player) e;
						if (!playerInPortal.contains(p)) {
							playerInPortal.add(p);
							Methods.join(p);
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
								@Override
								public void run() {
									playerInPortal.remove(p);
								}
							}, 140);
							
						}
					}
				}
				
				
			}
		}, 20, 20);
		
	}
	
	
}
