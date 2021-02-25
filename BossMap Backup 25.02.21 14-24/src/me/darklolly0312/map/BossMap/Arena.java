// 
// Decompiled by Procyon v0.5.36
// 

package me.darklolly0312.map.BossMap;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_16_R3.Entity;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import java.util.Random;
import org.bukkit.Bukkit;
import me.darklolly0312.map.BossMap.CustomEntity.Boss;
import org.bukkit.plugin.Plugin;

public class Arena {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    public static Boss boss;
    public static int scheduler1;
    public static int scheduler2;
    public static int scheduler3;
    public static int scheduler4;
    public static int scheduler5;
    public static boolean bossAbleToCast = true;
    
    public static void startEncounter() {
        init.isActive = true;
        spawnBoss();
        SpellHandler.startRandomCasting();
        Arena.scheduler1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Arena.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Arena.bossAbleToCast) {
                    final double rng1 = Math.random() * 100.0;
                    if (rng1 >= 80.0) {
                        final int id = new Random().nextInt(SpellHandler.spells + 1);
                        Bukkit.broadcastMessage("DEV1_ castrandomspell ID=" + id);
                        SpellHandler.cast(id);
                    }
                }
            }
        }, 20L, 20L);
    }
    
    public static void spawnBoss() {
        final Boss entity = new Boss(init.arena_bossSpawn);
        final WorldServer world = ((CraftWorld)Bukkit.getWorld(init.worldName)).getHandle();
        world.addEntity((Entity)entity);
        Arena.boss = entity;
        for (final Player p : init.joinedPlayers) {
            p.sendMessage(String.valueOf(Arena.boss.getName()) + " schreit: §oIhr wagt es MICH herauszufordern? Daf\u00fcr werdet ihr mit eurem Leben bezahlen!");
        }
        ParticleEffects.summonCircle(init.arena_bossSpawn);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Arena.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                ParticleEffects.summonCircle(init.arena_bossSpawn);
            }
        }, 5L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Arena.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                ParticleEffects.summonCircle(init.arena_bossSpawn);
            }
        }, 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Arena.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                ParticleEffects.summonCircle(init.arena_bossSpawn);
            }
        }, 15L);
    }
}
