package me.darklolly0312.map.BossMap;

import java.util.Random;
import me.darklolly0312.map.BossMap.bossSpells.napalm_bombs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SpellHandler {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    private static int timer_castHandler;
    public static int spells = 1;
    public static boolean forceCast = false;
    
    public static void cast(final int id) {
        if (!SpellHandler.forceCast) {
            if (id == 0) {
                napalm_bombs.use();
                Arena.bossAbleToCast = false;
                Bukkit.broadcastMessage("§cNapalmbomben!");
            }
            if (id == 1) {
                Arena.bossAbleToCast = false;
            }
            if (id == 2) {
                Arena.bossAbleToCast = false;
            }
            if (id == 3) {
                Arena.bossAbleToCast = false;
            }
            if (id == 4) {
                Arena.bossAbleToCast = false;
            }
            if (id == 5) {
                Arena.bossAbleToCast = false;
            }
            final int cooldown = 10;
            Bukkit.getScheduler().scheduleSyncDelayedTask(SpellHandler.pl, (Runnable)new Runnable() {
                @Override
                public void run() {
                    Arena.bossAbleToCast = true;
                }
            }, (long)(cooldown * 20));
        }
        else {
            Bukkit.broadcastMessage("cant cast spell(" + id + "), because forceCast is active!");
        }
    }
    
    public static void forceCast(final int id) {
        SpellHandler.forceCast = true;
        Bukkit.getScheduler().scheduleSyncDelayedTask(SpellHandler.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                SpellHandler.forceCast = false;
                Bukkit.broadcastMessage("boss can now cast random spells, deactivated forceCast");
            }
        }, 100L);
    }
    
    public static void startRandomCasting() {
        SpellHandler.timer_castHandler = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpellHandler.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Arena.bossAbleToCast) {
                    final double rng1 = Math.random() * 100.0;
                    if (rng1 >= 80.0) {
                        final int id = new Random().nextInt(SpellHandler.spells + 1);
                        SpellHandler.cast(id);
                    }
                }
            }
        }, 20L, 20L);
    }
    
    public static void stopRandomCasting() {
        Bukkit.getScheduler().cancelTask(SpellHandler.timer_castHandler);
    }
}
