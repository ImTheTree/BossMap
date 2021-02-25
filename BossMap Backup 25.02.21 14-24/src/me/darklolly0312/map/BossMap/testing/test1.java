package me.darklolly0312.map.BossMap.testing;

import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.Color;
import org.bukkit.Particle;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class test1 implements Listener {
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
    
    public static void use() {
        stopTimers();
        test1.particleLocs.clear();
        test1.direction = "plus";
        test1.sourceLoc = new Location(Bukkit.getWorld(init.worldName), 103.5, 16.1, -82.5);
        test1.loc = test1.sourceLoc;
        test1.minX = test1.sourceLoc.getX();
        test1.maxX = test1.sourceLoc.getX() + test1.maxRange;
        test1.particleLocs = new ArrayList<Location>();
        test1.timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(test1.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (test1.direction.equals("plus")) {
                    test1.loc.setX(test1.loc.getX() + 0.5);
                    test1.particleLocs.add(test1.loc);
                    for (final Location a : test1.getLineLocsZ(test1.loc)) {
                        test1.particleLocs.add(a);
                    }
                    if (test1.loc.getX() >= test1.maxX) {
                        test1.direction = "minus";
                    }
                }
                if (test1.direction.equals("minus")) {
                    test1.loc.setX(test1.loc.getX() - 0.5);
                    test1.particleLocs.add(test1.loc);
                    for (final Location a : test1.getLineLocsZ(test1.loc)) {
                        test1.particleLocs.add(a);
                    }
                    if (test1.loc.getX() <= test1.minX) {
                        test1.direction = "plus";
                    }
                }
                test1.loc.getWorld().spawnParticle(Particle.REDSTONE, test1.loc, 1, 0.0, 0.0, 0.0, 0.1, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }, 2L, 2L);
    }
    
    protected static ArrayList<Location> getLineLocsZ(final Location loc) {
        final Location tile = new Location(Bukkit.getWorld(init.worldName), loc.getX(), loc.getY(), loc.getZ());
        final ArrayList<Location> tiles = new ArrayList<Location>();
        final double multiply = 3.0;
        final int ti = (int)multiply;
        final double math = 1.0 / ti;
        for (int i = 0; i <= test1.maxLength * ti; ++i) {
            tile.setZ(tile.getZ() + math);
            tiles.add(tile);
            tile.getWorld().spawnParticle(Particle.REDSTONE, tile, 1, 0.0, 0.0, 0.0, 0.1, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            if (!tile.getWorld().getNearbyEntities(tile, 0.2, 0.2, 0.2).isEmpty()) {
                for (final Entity e : tile.getWorld().getNearbyEntities(tile, 0.2, 0.2, 0.2)) {
                    if (e instanceof Player) {
                        final Player p = (Player)e;
                        p.damage(6.0);
                    }
                }
            }
        }
        return tiles;
    }
    
    public static void startTimers() {
    }
    
    public static void stopTimers() {
        Bukkit.getScheduler().cancelTask(test1.timer1);
        Bukkit.getScheduler().cancelTask(test1.timer2);
    }
}
