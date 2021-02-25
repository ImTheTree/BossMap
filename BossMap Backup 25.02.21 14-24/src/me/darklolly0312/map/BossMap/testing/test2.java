package me.darklolly0312.map.BossMap.testing;

import me.darklolly0312.map.BossMap.LocationCalculator;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class test2 {
    static Plugin pl = Bukkit.getPluginManager().getPlugin("BossMap");
    static int timer;
    static Location loc;
    public static boolean enabled = false;
    
    public static void startTimer() {
        test2.timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(test2.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (test2.enabled) {
                    test2.loc = init.arena_mid;
                    if (test2.loc.getWorld().getNearbyEntities(test2.loc, 20.0, 20.0, 20.0) != null) {
                        for (final Entity e : test2.loc.getWorld().getNearbyEntities(test2.loc, 20.0, 20.0, 20.0)) {
                            if (e instanceof LivingEntity) {
                                LocationCalculator.drawLine(test2.loc, e.getLocation().add(0.0, 1.0, 0.0), Particle.CRIT, 0.2, 0.05, 0.0);
                            }
                        }
                    }
                }
            }
        }, 0L, 1L);
    }
    
    public static void stopTimer() {
        Bukkit.getScheduler().cancelTask(test2.timer);
    }
    
    public static void use() {
        if (test2.enabled) {
            test2.enabled = false;
        }
        else {
            test2.enabled = true;
        }
    }
}
