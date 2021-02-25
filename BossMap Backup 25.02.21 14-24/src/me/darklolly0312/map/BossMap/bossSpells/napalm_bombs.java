package me.darklolly0312.map.BossMap.bossSpells;

import org.bukkit.Color;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.entity.LivingEntity;
import me.darklolly0312.map.BossMap.EntityHandler;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Entity;
import me.darklolly0312.map.BossMap.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import java.util.ArrayList;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class napalm_bombs implements Listener {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    static int particleTrail;
    static int timer1;
    static World world;
    static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    
    @SuppressWarnings("deprecation")
	public static void use() {
        final Entity boss = (Entity) Arena.boss.getBukkitEntity();
        final double arrowsPerSec = 15.0;
        final double duration = 10.0;
        final Double arrowsDouble = new Double(arrowsPerSec);
        final Double durationDouble = new Double(duration);
        final Double temptick = new Double(durationDouble * 20.0 / (arrowsDouble * durationDouble));
        final int tick = temptick.intValue();
        timer1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int temp = 0;
            int max = arrowsDouble.intValue() * durationDouble.intValue();
            
            @Override
            public void run() {
                if (this.temp < this.max) {
                    createMissileArrow(boss.getLocation().add(0.0, 2.25, 0.0));
                }
                else {
                    Bukkit.getScheduler().cancelTask(napalm_bombs.timer1);
                }
                ++this.temp;
            }
        }, (long)tick, (long)tick);
    }
    
    @EventHandler
    public void onArrowCollide(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            final Arrow a = (Arrow)e.getEntity();
            if (napalm_bombs.arrows.contains(a)) {
                this.createExplosion(a.getLocation());
                a.teleport(a.getLocation().add(0.0, -200.0, 0.0));
                napalm_bombs.arrows.remove(a);
                a.remove();
            }
        }
    }
    
    @EventHandler
    public void ArrowDamagePrevent(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow && napalm_bombs.arrows.contains(e.getDamager())) {
            final Arrow a = (Arrow)e.getDamager();
            napalm_bombs.arrows.remove(a);
            a.remove();
            Bukkit.broadcastMessage("dev, cancelled EntityDamageByEntityEvent");
            e.setCancelled(true);
        }
    }
    
    private static void createMissileArrow(final Location loc) {
        final Arrow arrow = loc.getWorld().spawnArrow(loc, loc.getDirection(), 0.0f, 0.0f);
        arrow.setDamage(0.0);
        arrow.setKnockbackStrength(0);
        arrow.setSilent(true);
        arrow.setPersistent(true);
        final double rnd = Math.random();
        final Vector v = arrow.getVelocity();
        if (rnd >= 0.5) {
            double d1 = Math.random();
            d1 -= d1 * 2.0;
            double d2 = Math.random();
            d2 -= d2 * 2.0;
            v.setX(d1);
            v.setY(Math.random() * 1.3 + 0.08);
            v.setZ(d2);
        }
        else {
            v.setX(Math.random() / 2.0);
            v.setY(Math.random() * 1.3 + 0.08);
            v.setZ(Math.random() / 2.0);
        }
        arrow.setVelocity(v);
        napalm_bombs.arrows.add(arrow);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[] { ((CraftArrow)arrow).getHandle().getId() }));
        }
    }
    
    private void createExplosion(final Location loc) {
        loc.getWorld().spawnParticle(Particle.FLAME, loc, 10, 0.0, 0.0, 0.0, 0.1);
        loc.getWorld().spawnParticle(Particle.LAVA, loc, 10, 0.0, 0.0, 0.0, 0.1);
        loc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 75, 0.0, 0.0, 0.0, 0.1);
        for (final Entity e : EntityHandler.getEntitiesByLocation(loc, 1.0f)) {
            if (e instanceof Player) {
                final LivingEntity le;
                final Player p = (Player)(le = (LivingEntity)e);
                le.setNoDamageTicks(0);
                p.setInvulnerable(false);
                p.damage(8.0);
            }
        }
    }
    
    public static void startTimers() {
        napalm_bombs.world = Bukkit.getWorld(init.worldName);
        napalm_bombs.particleTrail = Bukkit.getScheduler().scheduleSyncRepeatingTask(napalm_bombs.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (napalm_bombs.world != null && !napalm_bombs.arrows.isEmpty()) {
                    for (final Arrow a : napalm_bombs.arrows) {
                        if (!a.isOnGround()) {
                            napalm_bombs.world.spawnParticle(Particle.REDSTONE, a.getLocation(), 3, Math.random() / 6.0, Math.random() / 6.0, Math.random() / 6.0, 0.3, (Object)new Particle.DustOptions(Color.RED, 1.0f));
                            napalm_bombs.world.spawnParticle(Particle.REDSTONE, a.getLocation(), 1, Math.random() / 8.0, Math.random() / 8.0, Math.random() / 8.0, 0.3, (Object)new Particle.DustOptions(Color.ORANGE, 1.0f));
                        }
                    }
                }
            }
        }, 0L, 0L);
        System.out.println("napalmbombs timers started");
    }
    
    public static void stopTimers() {
        Bukkit.getScheduler().cancelTask(napalm_bombs.particleTrail);
        Bukkit.getScheduler().cancelTask(napalm_bombs.timer1);
    }
}
