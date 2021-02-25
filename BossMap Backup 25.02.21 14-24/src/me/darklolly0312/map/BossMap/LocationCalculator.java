package me.darklolly0312.map.BossMap;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.util.Vector;
import org.bukkit.Particle;
import org.bukkit.World;
import java.util.Random;
import java.util.ArrayList;
import org.bukkit.block.Block;
import java.util.List;
import org.bukkit.Location;

public class LocationCalculator {
	
    public static List<Block> getCuboidBlocks(final Location Pos1, final Location Pos2) {
        final List<Block> blocks = new ArrayList<Block>();
        final int minx = Math.min(Pos1.getBlockX(), Pos2.getBlockX());
        final int miny = Math.min(Pos1.getBlockY(), Pos2.getBlockY());
        final int minz = Math.min(Pos1.getBlockZ(), Pos2.getBlockZ());
        final int maxx = Math.max(Pos1.getBlockX(), Pos2.getBlockX());
        final int maxy = Math.max(Pos1.getBlockY(), Pos2.getBlockY());
        final int maxz = Math.max(Pos1.getBlockZ(), Pos2.getBlockZ());
        for (int x = minx; x <= maxx; ++x) {
            for (int y = miny; y <= maxy; ++y) {
                for (int z = minz; z <= maxz; ++z) {
                    blocks.add(Pos1.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
    
    public static List<Block> getCircleBlocks(final Location loc, final double radius) {
        final List<Block> blocks = new ArrayList<Block>();
        final double PI = 3.141592653589793;
        final double x = loc.getX();
        final double y = loc.getZ();
        for (int faktor = 0; faktor <= 150; ++faktor) {
            final float faktordezimal = faktor / 150.0f;
            final float xp = (float)radius * (float)Math.cos(faktordezimal * 2.0f * PI);
            final float yp = (float)radius * (float)Math.sin(faktordezimal * 2.0f * PI);
            final Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
            blocks.add(tile.getBlock());
        }
        return blocks;
    }
    
    public static ArrayList<Location> getSphereLocations(final Location loc, final double radius) {
        final ArrayList<Location> locs = new ArrayList<Location>();
        for (double i = 0.0; i <= 3.141592653589793; i += 0.3141592653589793) {
            final double r = Math.sin(i) * radius;
            final double y = Math.cos(i) * radius;
            for (double a = 0.0; a < 6.283185307179586; a += 0.3141592653589793) {
                final double x = Math.cos(a) * r;
                final double z = Math.sin(a) * r;
                loc.add(x, y, z);
                final Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                locs.add(newLoc);
                loc.subtract(x, y, z);
            }
        }
        return locs;
    }
    
    public static List<Location> getCircleLocations(final Location loc, final double radius) {
        final List<Location> locations = new ArrayList<Location>();
        final double PI = 3.141592653589793;
        final double x = loc.getX();
        final double y = loc.getZ();
        for (int faktor = 0; faktor <= 150; ++faktor) {
            final float faktordezimal = faktor / 150.0f;
            final float xp = (float)radius * (float)Math.cos(faktordezimal * 2.0f * PI);
            final float yp = (float)radius * (float)Math.sin(faktordezimal * 2.0f * PI);
            final Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
            locations.add(tile);
        }
        return locations;
    }
    
    public static List<Location> getRandomCircleLocations(final Location loc, final double radius, final int percentage) {
        final List<Location> locations = new ArrayList<Location>();
        final double PI = 3.141592653589793;
        final double x = loc.getX();
        final double y = loc.getZ();
        for (int faktor = 0; faktor <= 150; ++faktor) {
            final float faktordezimal = faktor / 150.0f;
            final float xp = (float)radius * (float)Math.cos(faktordezimal * 2.0f * PI);
            final float yp = (float)radius * (float)Math.sin(faktordezimal * 2.0f * PI);
            final Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
            final int rng = new Random().nextInt(100);
            if (rng > 0 && rng <= percentage) {
                locations.add(tile);
            }
        }
        return locations;
    }
    
    public static List<Location> getRandomCuboidLocations(final Location Pos1, final Location Pos2, final int percentage) {
        final World world = Pos1.getWorld();
        final List<Location> locations = new ArrayList<Location>();
        final double minx = Math.min(Pos1.getX(), Pos2.getX());
        final double miny = Math.min(Pos1.getY(), Pos2.getY());
        final double minz = Math.min(Pos1.getZ(), Pos2.getZ());
        final double maxx = Math.max(Pos1.getX(), Pos2.getX());
        final double maxy = Math.max(Pos1.getY(), Pos2.getY());
        final double maxz = Math.max(Pos1.getZ(), Pos2.getZ());
        for (double x = minx; x <= maxx; ++x) {
            for (double y = miny; y <= maxy; ++y) {
                for (double z = minz; z <= maxz; ++z) {
                    final int rng = new Random().nextInt(100);
                    if (rng > 0 && rng <= percentage) {
                        final double xRng = new Random().nextDouble();
                        final double yRng = new Random().nextDouble();
                        final double zRng = new Random().nextDouble();
                        final Location tile = new Location(world, x, y, z);
                        tile.setX(x + 0.5 - xRng);
                        tile.setY(y + 0.5 - yRng);
                        tile.setZ(z + 0.5 - zRng);
                        locations.add(tile);
                    }
                }
            }
        }
        return locations;
    }
    
    public static Location getRandomSquareLocation(final Location loc1, final Location loc2) {
        final double minX = Math.min(loc1.getX(), loc2.getX());
        final double minY = Math.min(loc1.getY(), loc2.getY());
        final double minZ = Math.min(loc1.getZ(), loc2.getZ());
        final double maxX = Math.max(loc1.getX(), loc2.getX());
        final double maxY = Math.max(loc1.getY(), loc2.getY());
        final double maxZ = Math.max(loc1.getZ(), loc2.getZ());
        return new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
    }
    
    public static Location getRandomSquareAirLocation(final Location loc1, final Location loc2) {
        final double minX = Math.min(loc1.getX(), loc2.getX());
        final double minY = Math.min(loc1.getY(), loc2.getY());
        final double minZ = Math.min(loc1.getZ(), loc2.getZ());
        final double maxX = Math.max(loc1.getX(), loc2.getX());
        final double maxY = Math.max(loc1.getY(), loc2.getY());
        final double maxZ = Math.max(loc1.getZ(), loc2.getZ());
        final Location randomLoc = new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
        return randomLoc;
    }
    
    public static List<Location> getLineLocations(final Location loc1, final Location loc2, final double space) {
        final ArrayList<Location> locs = new ArrayList<Location>();
        final World world = loc1.getWorld();
        final double distance = loc1.distance(loc2);
        final Vector p1 = loc1.toVector();
        final Vector p2 = loc2.toVector();
        final Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0.0;
        while (length < distance) {
            locs.add(new Location(world, p1.getX(), p1.getY(), p1.getZ()));
            world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1);
            length += space;
            p1.add(vector);
        }
        return locs;
    }
    
    public static void drawLine(final Location loc1, final Location loc2, final Particle type, final double space, final double offset, final double speed) {
        final World world = loc1.getWorld();
        final double distance = loc1.distance(loc2);
        final Vector p1 = loc1.toVector();
        final Vector p2 = loc2.toVector();
        final Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0.0;
        while (length < distance) {
            world.spawnParticle(type, p1.getX(), p1.getY(), p1.getZ(), 1, offset, offset, offset, speed);
            length += space;
            p1.add(vector);
        }
    }
    
    public static double randomDouble(final double min, final double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1.0));
    }
}
