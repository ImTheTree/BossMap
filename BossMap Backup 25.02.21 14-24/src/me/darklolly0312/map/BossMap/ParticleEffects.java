package me.darklolly0312.map.BossMap;

import org.bukkit.entity.Player;
import java.util.List;
import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ParticleEffects {
    static int ID;
    static int test_ID;
    static boolean test = true;
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    static int timer1;
    
    public static void Teleport(final Location loc, final int speed) {
        Bukkit.broadcastMessage("NOTHING");
    }
    
    public static void summonCircle(final Location loc) {
        final Location tloc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        List<Location> locs = null;
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
        }
        tloc.setY(tloc.getY() + 0.05);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 5) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.1);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 25) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.15);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 40) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.25);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 55) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.35);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 70) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.5);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 85) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
        tloc.setY(tloc.getY() + 0.75);
        locs = LocationCalculator.getCircleLocations(tloc, 0.8);
        for (int i = 0; i < locs.toArray().length; ++i) {
            if (new Random().nextInt(100) + 1 > 95) {
                locs.get(i).getWorld().spawnParticle(Particle.REDSTONE, locs.get(i).getX(), locs.get(i).getY(), locs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
            }
        }
    }
    
    public static void fungraphic(final Location loc) {
        List<Location> locs = null;
        loc.setX(loc.getX() - 0.5);
        loc.setZ(loc.getZ() - 0.5);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() + 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setX(loc.getX() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() - 0.2);
        locs = LocationCalculator.getCircleLocations(loc, 2.0);
        for (int i = 0; i < locs.toArray().length; ++i) {
            locs.get(i).getWorld().spawnParticle(Particle.FLAME, (Location)locs.get(i), 1, 0.0, 0.0, 0.0, 0.0);
        }
        loc.setZ(loc.getZ() - 0.2);
    }
    
    public static void test1(final Player p) {
        final Location minLoc = p.getLocation();
        final Location topLoc = p.getLocation().add(0.0, 10.0, 0.0);
        ParticleEffects.timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(ParticleEffects.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (topLoc.getY() > minLoc.getY()) {
                    topLoc.setY(topLoc.getY() - 0.3);
                    topLoc.getWorld().spawnParticle(Particle.REDSTONE, topLoc.getX(), topLoc.getY(), topLoc.getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
                    minLoc.getWorld().spawnParticle(Particle.REDSTONE, minLoc.getX(), minLoc.getY(), minLoc.getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.BLUE, 1.0f));
                    return;
                }
                Bukkit.getScheduler().cancelTask(ParticleEffects.timer1);
            }
        }, 3L, 3L);
    }
    
    public static void rainIndicator(final Location tl) {
        final Location loc = tl;
        final List<Location> cLocs = LocationCalculator.getCircleLocations(loc, 1.0);
        for (int i = 0; i < cLocs.toArray().length; ++i) {
            cLocs.get(i).getWorld().spawnParticle(Particle.REDSTONE, cLocs.get(i).getX(), cLocs.get(i).getY(), cLocs.get(i).getZ(), 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
        }
    }
}
