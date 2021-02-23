package me.darklolly0312.map.BossMap.testing;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class test9 implements Listener{
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	static int timer;

	public static void use(Player p) { // example (spell) test code --------------------------------------
		
	}
	
	// example event
	/*@EventHandler
	public void onArrowCollide(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if (arrows.contains(a)) {
				createExplosion(a.getLocation());
				a.teleport(a.getLocation().add(0,-200,0));
				arrows.remove(a);
				a.remove();
			}
		}
	}*/

	
	public static void startTimers() {
		timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
			   
			}
		}, 0, 0);
	}
	
	
	public static void stopTimers() {
		Bukkit.getScheduler().cancelTask(timer);
	}
	
	

}











