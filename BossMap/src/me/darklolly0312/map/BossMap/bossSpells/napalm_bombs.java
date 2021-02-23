package me.darklolly0312.map.BossMap.bossSpells;

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
import org.bukkit.util.Vector;

import me.darklolly0312.map.BossMap.Arena;
import me.darklolly0312.map.BossMap.EntityHandler;
import me.darklolly0312.map.BossMap.init;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;

public class napalm_bombs implements Listener{
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	static int particleTrail;
	static int timer1;
	static World world;
	static ArrayList<Arrow> arrows = new ArrayList<Arrow>();

	@SuppressWarnings("deprecation")
	public static void use() { // napalm bombs code --------------------------------------
		Entity boss = Arena.boss.getBukkitEntity();
		// î casted CustomEntity to normal entity  above for code to work
		
		double arrowsPerSec = 15; // arrowsPerSec x duration = totalAmountOfArrows
		double duration 	= 10;
		
		Double arrowsDouble = new Double(arrowsPerSec);	
		Double durationDouble = new Double(duration);
		Double temptick = new Double((durationDouble*20) / (arrowsDouble*durationDouble));
		int tick = temptick.intValue();
		timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			int temp = 0;
			int max = arrowsDouble.intValue() * durationDouble.intValue();
			@Override
			public void run() {
				if (temp < max) {
					createMissileArrow(boss.getLocation().add(0, 2.25, 0));
				} else {
					Bukkit.getScheduler().cancelTask(timer1);				
				}
				temp++;
			}
		}, tick, tick);
	}
	
	@EventHandler
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
	}
	
	@EventHandler
	public void ArrowDamagePrevent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow) {
			if (arrows.contains((Arrow) e.getDamager())) {
				Arrow a = (Arrow) e.getDamager();
				arrows.remove(a);
				a.remove();
				Bukkit.broadcastMessage("dev, cancelled EntityDamageByEntityEvent");
				e.setCancelled(true);
			}
		}
	}

	private static void createMissileArrow(Location loc) {
		Arrow arrow = loc.getWorld().spawnArrow(loc, loc.getDirection(), 0, 0);
			arrow.setDamage(0.0);
			arrow.setKnockbackStrength(0);
			arrow.setSilent(true);
			arrow.setPersistent(true);
			double rnd = Math.random();

			Vector v = arrow.getVelocity();
			if (rnd >= 0.5) {
				double d1 = Math.random();
				d1 = d1 - (d1*2);
				
				double d2 = Math.random();
				d2 = d2 - (d2*2);
				
				v.setX(d1);
				v.setY((Math.random()*1.3)+0.08);
				v.setZ(d2);
			} else {
				v.setX(Math.random() / 2);
				v.setY((Math.random()*1.3)+0.08);
				v.setZ(Math.random() / 2);
			}
			arrow.setVelocity(v);
		
		arrows.add(arrow);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(((CraftArrow) arrow).getHandle().getId()));
        }
	}
	
	
	private void createExplosion(Location loc) {
	    loc.getWorld().spawnParticle(Particle.FLAME, loc, 10, 0, 0 ,0, 0.1);
	    loc.getWorld().spawnParticle(Particle.LAVA, loc, 10, 0, 0 ,0, 0.1);
	    loc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 75, 0, 0 ,0, 0.1);
	    for(Entity e : EntityHandler.getEntitiesByLocation(loc, 1)) {
			if (e instanceof Player) {
				Player p = (Player) e;
				LivingEntity le = (LivingEntity) p;
				le.setNoDamageTicks(0);
				p.setInvulnerable(false);
				p.damage(8.0);
			}
		}
	}

	
	public static void startTimers() {
		world = Bukkit.getWorld(init.worldName);
		
		particleTrail = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
			    if (world != null && !arrows.isEmpty()) {
			    	for (Arrow a : arrows) {
			    		if (!a.isOnGround()) {
			    			world.spawnParticle(Particle.REDSTONE, a.getLocation(), 3, Math.random() / 6, Math.random() / 6, Math.random() / 6, 0.3, new Particle.DustOptions(Color.RED,1));
						    world.spawnParticle(Particle.REDSTONE, a.getLocation(), 1, Math.random() / 8, Math.random() / 8, Math.random() / 8, 0.3, new Particle.DustOptions(Color.ORANGE,1));
			    		}
			    	}
			    }
			}
		}, 0, 0);
		System.out.println("napalmbombs timers started");
	}
	
	
	public static void stopTimers() {
		Bukkit.getScheduler().cancelTask(particleTrail);
		Bukkit.getScheduler().cancelTask(timer1);
	}
	
	

}











