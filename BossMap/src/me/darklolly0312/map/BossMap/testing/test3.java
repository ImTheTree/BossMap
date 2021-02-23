package me.darklolly0312.map.BossMap.testing;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;

import me.darklolly0312.map.BossMap.EntityHandler;
import me.darklolly0312.map.BossMap.init;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;

public class test3 implements Listener {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	static int indicator;
	static int particleTrail;
	static World world;
	static Location sourceLoc;
	static ArrayList<Location> indicators = new ArrayList<Location>();
	static ArrayList<Location> sourceLocs = new ArrayList<Location>();
	static ArrayList<Arrow> arrows = new ArrayList<Arrow>();

	public static void use(Player p) { // meteor code (TODO multiple meteors)
		Location sourceLoc = p.getLocation();
		sourceLocs.add(sourceLoc);
		world = p.getWorld();
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			@Override
			public void run() {
				createMissileArrow(sourceLoc.add(0, 20, 0));
			}
		}, 20);
	}
	
	
	@EventHandler
	public void onArrowCollide(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if (arrows.contains(a)) {
				createExplosion(a.getLocation());
				a.teleport(a.getLocation().add(0,-200,0));
				if (e.getHitBlock() != null) {
					arrows.remove(a);
					a.remove();
				}
				if (e.getHitEntity() != null) {
					if (e.getHitEntity() instanceof LivingEntity) {
						//LivingEntity le = (LivingEntity) e.getHitEntity(); le.setInvulnerable(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void ArrowDamagePrevent(EntityDamageByEntityEvent e) {
		Bukkit.broadcastMessage("Arrow damage event");
		if (e.getDamager() instanceof Arrow) {
			if (arrows.contains((Arrow) e.getDamager())) {
				Arrow a = (Arrow) e.getDamager();
				arrows.remove(a);
				a.remove();
				e.setCancelled(true);
			}
		}
	}

	private static void createMissileArrow(Location loc) {
		Arrow arrow = loc.getWorld().spawnArrow(loc.add(0,20,0), loc.getDirection(), 0, 0);
			arrow.setDamage(10.0);
			arrow.setKnockbackStrength(0);
			arrow.setSilent(true);
			arrow.setPersistent(true);
		
		arrows.add(arrow);
		sourceLocs.remove(loc);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(((CraftArrow) arrow).getHandle().getId()));
        }
	}
	
	
	private void createExplosion(Location loc) {
	    loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 250, 0, Math.random() / 4,0, 0.3);
	    loc.getWorld().spawnParticle(Particle.LAVA, loc, 80, 0, 0 ,0, 0.1);
	    for(Entity e : EntityHandler.getEntitiesByLocation(loc, 5)) {
			if (e instanceof Player) {
				Player p = (Player) e;
				LivingEntity le = (LivingEntity) p;
				le.setNoDamageTicks(0);
				p.setInvulnerable(false);
				p.damage(19.0);
			}
		}
	}

	
	public static void startTimers() {
		world = Bukkit.getWorld(init.worldName);
		
		indicator = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() { // Ground-Indicator
			@Override
			public void run() {
				if (world != null && !sourceLocs.isEmpty()) {
					for (Location l : sourceLocs) {
						world.spawnParticle(Particle.REDSTONE, l, 0, 0.5, 0.0, 0.5, new Particle.DustOptions(Color.RED,1));
					}
				}
			}
		}, 5, 5);
		
		particleTrail = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
			    if (world != null && !arrows.isEmpty()) {
			    	for (Arrow a : arrows) {
					    world.spawnParticle(Particle.REDSTONE, a.getLocation(), 20, Math.random() / 4, Math.random() / 4, Math.random() / 4, 0.3, new Particle.DustOptions(Color.RED,1));
					    world.spawnParticle(Particle.REDSTONE, a.getLocation(), 7, Math.random() / 6, Math.random() / 6, Math.random() / 6, 0.3, new Particle.DustOptions(Color.ORANGE,1));
					    world.spawnParticle(Particle.FLAME, a.getLocation(), 20, 0, 0 ,0, 0.1);
					    world.spawnParticle(Particle.LAVA, a.getLocation(), 20, 0, 0 ,0, 0.1);
			    	}
			    }
			}
		}, 0, 0);
	}
	
	
	public static void stopTimers() {
		Bukkit.getScheduler().cancelTask(indicator);
		Bukkit.getScheduler().cancelTask(particleTrail);
	}
	
	

}




























































































