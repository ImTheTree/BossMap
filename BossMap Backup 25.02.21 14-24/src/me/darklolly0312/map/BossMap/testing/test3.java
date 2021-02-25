package me.darklolly0312.map.BossMap.testing;

import org.bukkit.Color;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.entity.Entity;
import me.darklolly0312.map.BossMap.EntityHandler;
import org.bukkit.Particle;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class test3 implements Listener {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    static int indicator;
    static int particleTrail;
    static World world;
    static Location sourceLoc;
    static ArrayList<Location> indicators = new ArrayList<Location>();
    static ArrayList<Location> sourceLocs = new ArrayList<Location>();
    static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    
    public static void use(final Player p) {
        final Location sourceLoc = p.getLocation();
        test3.sourceLocs.add(sourceLoc);
        test3.world = p.getWorld();
        Bukkit.getScheduler().scheduleSyncDelayedTask(test3.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                createMissileArrow(sourceLoc.add(0.0, 20.0, 0.0));
            }
        }, 20L);
    }
    
    @SuppressWarnings("unused")
	@EventHandler
    public void onArrowCollide(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            final Arrow a = (Arrow)e.getEntity();
            if (test3.arrows.contains(a)) {
                this.createExplosion(a.getLocation());
                a.teleport(a.getLocation().add(0.0, -200.0, 0.0));
                if (e.getHitBlock() != null) {
                    test3.arrows.remove(a);
                    a.remove();
                }
                if (e.getHitEntity() != null) {
                    final boolean b = e.getHitEntity() instanceof LivingEntity;
                    Bukkit.broadcastMessage("BROKEN CODE? test3(59)");
                }
            }
        }
    }
    
    @EventHandler
    public void ArrowDamagePrevent(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow && test3.arrows.contains(e.getDamager())) {
            final Arrow a = (Arrow)e.getDamager();
            test3.arrows.remove(a);
            a.remove();
            e.setCancelled(true);
        }
    }
    
    private static void createMissileArrow(final Location loc) {
        final Arrow arrow = loc.getWorld().spawnArrow(loc.add(0.0, 20.0, 0.0), loc.getDirection(), 0.0f, 0.0f);
        arrow.setDamage(10.0);
        arrow.setKnockbackStrength(0);
        arrow.setSilent(true);
        arrow.setPersistent(true);
        test3.arrows.add(arrow);
        test3.sourceLocs.remove(loc);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[] { ((CraftArrow)arrow).getHandle().getId() }));
        }
    }
    
    private void createExplosion(final Location loc) {
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 250, 0.0, Math.random() / 4.0, 0.0, 0.3);
        loc.getWorld().spawnParticle(Particle.LAVA, loc, 80, 0.0, 0.0, 0.0, 0.1);
        for (final Entity e : EntityHandler.getEntitiesByLocation(loc, 5.0f)) {
            if (e instanceof Player) {
                final LivingEntity le;
                final Player p = (Player)(le = (LivingEntity)e);
                le.setNoDamageTicks(0);
                p.setInvulnerable(false);
                p.damage(19.0);
            }
        }
    }
    
    public static void startTimers() {
        test3.world = Bukkit.getWorld(init.worldName);
        test3.indicator = Bukkit.getScheduler().scheduleSyncRepeatingTask(test3.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (test3.world != null && !test3.sourceLocs.isEmpty()) {
                    for (final Location l : test3.sourceLocs) {
                        test3.world.spawnParticle(Particle.REDSTONE, l, 0, 0.5, 0.0, 0.5, (Object)new Particle.DustOptions(Color.RED, 1.0f));
                    }
                }
            }
        }, 5L, 5L);
        test3.particleTrail = Bukkit.getScheduler().scheduleSyncRepeatingTask(test3.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (test3.world != null && !test3.arrows.isEmpty()) {
                    for (final Arrow a : test3.arrows) {
                        test3.world.spawnParticle(Particle.REDSTONE, a.getLocation(), 20, Math.random() / 4.0, Math.random() / 4.0, Math.random() / 4.0, 0.3, (Object)new Particle.DustOptions(Color.RED, 1.0f));
                        test3.world.spawnParticle(Particle.REDSTONE, a.getLocation(), 7, Math.random() / 6.0, Math.random() / 6.0, Math.random() / 6.0, 0.3, (Object)new Particle.DustOptions(Color.ORANGE, 1.0f));
                        test3.world.spawnParticle(Particle.FLAME, a.getLocation(), 20, 0.0, 0.0, 0.0, 0.1);
                        test3.world.spawnParticle(Particle.LAVA, a.getLocation(), 20, 0.0, 0.0, 0.0, 0.1);
                    }
                }
            }
        }, 0L, 0L);
    }
    
    public static void stopTimers() {
        Bukkit.getScheduler().cancelTask(test3.indicator);
        Bukkit.getScheduler().cancelTask(test3.particleTrail);
    }
}
