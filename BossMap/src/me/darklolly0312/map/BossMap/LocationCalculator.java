package me.darklolly0312.map.BossMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class LocationCalculator {
	

	
	public static List<Block> getCuboidBlocks(Location Pos1, Location Pos2) {
        
		List<Block> blocks = new ArrayList<Block>();
        
        int minx = Math.min(Pos1.getBlockX(), Pos2.getBlockX()),
        miny = Math.min(Pos1.getBlockY(), Pos2.getBlockY()),
        minz = Math.min(Pos1.getBlockZ(), Pos2.getBlockZ()),
        maxx = Math.max(Pos1.getBlockX(), Pos2.getBlockX()),
        maxy = Math.max(Pos1.getBlockY(), Pos2.getBlockY()),
        maxz = Math.max(Pos1.getBlockZ(), Pos2.getBlockZ());
        
        
        for (int x = minx; x<=maxx;x++) {
            for (int y = miny; y<=maxy;y++) {
                for (int z = minz; z<=maxz;z++) {
                    blocks.add(Pos1.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

	
	public static List<Block> getCircleBlocks(Location loc, double radius) {
		List<Block> blocks = new ArrayList<Block>();
		
		double PI = (double) 3.141592653589793238462643383279502884197169399375105820974944592307816406286208995;
		double x = (double) loc.getX();
		double y = (double) loc.getZ();
		for (int faktor = 0; faktor <= 150; faktor++) {
			float faktordezimal = (float) faktor / 150;
			float xp = (float) radius * (float) Math.cos(faktordezimal * 2 * PI);
			float yp = (float) radius * (float) Math.sin(faktordezimal * 2 * PI);
			Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
			blocks.add(tile.getBlock());
		}
		
		return blocks;
	}
	
	public static ArrayList<Location> getSphereLocations(Location loc, double radius) { // not working somehow, works in "/test 17 <radius>" command :/
        ArrayList<Location> locs = new ArrayList<Location>();
        
        
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) { // 10 = ring count?
			double r = Math.sin(i) * radius;
        	double y = Math.cos(i) * radius;
        	for (double a = 0; a < Math.PI * 2; a+= Math.PI / 10) { // 10 = ring count?
        		double x = Math.cos(a) * r;
        		double z = Math.sin(a) * r;
        		loc.add(x, y, z);
        		Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        		locs.add(newLoc);
        		loc.subtract(x, y, z);
        	}
        }
        
        
        return locs;
	}
	
	public static List<Location> getCircleLocations(Location loc, double radius) {
		List<Location> locations = new ArrayList<Location>();
		
		double PI = (double) 3.141592653589793238462643383279502884197169399375105820974944592307816406286208995;
		double x = (double) loc.getX();
		double y = (double) loc.getZ();
		for (int faktor = 0; faktor <= 150; faktor++) {
			float faktordezimal = (float) faktor / 150;
			float xp = (float) radius * (float) Math.cos(faktordezimal * 2 * PI);
			float yp = (float) radius * (float) Math.sin(faktordezimal * 2 * PI);
			Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
			locations.add(tile);
		}
		
		return locations;
	}
	
	public static List<Location> getRandomCircleLocations(Location loc, double radius, int percentage) {
		List<Location> locations = new ArrayList<Location>();
		
		double PI = (double) 3.141592653589793238462643383279502884197169399375105820974944592307816406286208995;
		double x = (double) loc.getX();
		double y = (double) loc.getZ();
		for (int faktor = 0; faktor <= 150; faktor++) {
			float faktordezimal = (float) faktor / 150;
			float xp = (float) radius * (float) Math.cos(faktordezimal * 2 * PI);
			float yp = (float) radius * (float) Math.sin(faktordezimal * 2 * PI);
			Location tile = new Location(loc.getWorld(), x + xp, loc.getY(), y + yp);
			
			int rng = new Random().nextInt(100);
			if (rng > 0 && rng <= percentage) {
				locations.add(tile);
			}
		}
		
		return locations;
	}
	
	public static List<Location> getRandomCuboidLocations(Location Pos1, Location Pos2, int percentage) { // TODO <-- not working correctly, gets 1 location per block atm
        
		World world = Pos1.getWorld();
		List<Location> locations = new ArrayList<Location>();
        
        double minx = Math.min(Pos1.getX(), Pos2.getX()),
        miny = Math.min(Pos1.getY(), Pos2.getY()),
        minz = Math.min(Pos1.getZ(), Pos2.getZ()),
        maxx = Math.max(Pos1.getX(), Pos2.getX()),
        maxy = Math.max(Pos1.getY(), Pos2.getY()),
        maxz = Math.max(Pos1.getZ(), Pos2.getZ());
        
        
        for (double x = minx; x<=maxx;x++) { // test cmd: /test 16 10 744.5 4.1 320.5 750.5 4.1 317.5 <-- in d1 world at the 2 stone-blocks behind world-spawn
            for (double y = miny; y<=maxy;y++) {
                for (double z = minz; z<=maxz;z++) {
                	int rng = new Random().nextInt(100);
        			if (rng > 0 && rng <= percentage) {
        				double xRng = new Random().nextDouble();
        				double yRng = new Random().nextDouble();
        				double zRng = new Random().nextDouble();
        				
        				Location tile = new Location(world, x, y, z);
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
	
	public static Location getRandomSquareLocation(Location loc1, Location loc2) {
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
    }
	
	public static Location getRandomSquareAirLocation(Location loc1, Location loc2) {
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());
        
        Location randomLoc = new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
		return randomLoc;
    }

	public static List<Location> getLineLocations(Location loc1, Location loc2, double space) { // broken ----------------------------------------
		ArrayList<Location> locs = new ArrayList<Location>();
		
		World world = loc1.getWorld();
	    double distance = loc1.distance(loc2);
	    Vector p1 = loc1.toVector();
	    Vector p2 = loc2.toVector();
	    Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
	    double length = 0;
	    for (; length < distance; p1.add(vector)) {
	    	locs.add(new Location(world, p1.getX(), p1.getY(), p1.getZ()));
	        world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1);
	        length += space;
	    }
		
		return locs;
	}
	
	public static void drawLine(Location loc1, Location loc2, Particle type, double space, double offset, double speed) {
	    World world = loc1.getWorld();
	    double distance = loc1.distance(loc2);
	    Vector p1 = loc1.toVector();
	    Vector p2 = loc2.toVector();
	    Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
	    double length = 0;
	    for (; length < distance; p1.add(vector)) {
	    	

	    	world.spawnParticle(type, p1.getX(), p1.getY(), p1.getZ(), 1, offset, offset , offset, speed/*, new Particle.DustOptions(Color.RED,1)*/);
	        
	        
	        length += space;
	    }
	}
	
    public static double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
