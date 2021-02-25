package me.darklolly0312.map.BossMap.testing;

import org.bukkit.Color;
import org.bukkit.Particle;
import me.darklolly0312.map.BossMap.Arena;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.Location;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class rain {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    static int timer1;
    static int timer2;
    
    public static void use() {
        Bukkit.broadcastMessage("rain activated");
        final ArrayList<Location> locs = new ArrayList<Location>();
        rain.timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(rain.pl, (Runnable)new Runnable() {
            int count = 0;
            
            @Override
            public void run() {
                if (this.count < 100) {
                    final Location tl = new Location(Bukkit.getWorld(init.worldName), Arena.boss.locX(), Arena.boss.locY(), Arena.boss.locZ());
                    locs.add(tl);
                }
                if (this.count >= 100) {
                    Bukkit.getScheduler().cancelTask(rain.timer1);
                }
                this.count += 10;
            }
        }, 10L, 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(rain.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Location l : locs) {
                    final Location upperLoc = l.add(0.0, 10.0, 0.0);
                    rain.timer2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(rain.pl, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (upperLoc.getY() >= l.getY()) {
                                upperLoc.setY(upperLoc.getY() - 0.3);
                            }
                            if (upperLoc.getY() <= l.getY()) {
                                Bukkit.getScheduler().cancelTask(rain.timer2);
                            }
                            for (final Location tl : locs) {
                                tl.getWorld().spawnParticle(Particle.REDSTONE, tl.getX(), tl.getY(), tl.getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
                            }
                        }
                    }, 10L, 10L);
                }
            }
        }, 101L);
    }
}
