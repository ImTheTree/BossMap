package me.darklolly0312.map.BossMap.testing;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class test9 implements Listener {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    static int timer;
    
    public static void use(final Player p) {
    }
    
    public static void startTimers() {
        test9.timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(test9.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
            }
        }, 0L, 0L);
    }
    
    public static void stopTimers() {
        Bukkit.getScheduler().cancelTask(test9.timer);
    }
}
