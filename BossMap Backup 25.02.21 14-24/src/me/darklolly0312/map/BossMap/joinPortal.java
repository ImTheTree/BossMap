package me.darklolly0312.map.BossMap;

import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

public class joinPortal implements Listener {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    public static ArrayList<Player> playerInPortal = new ArrayList<Player>();
    
    public static void startPortal() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(joinPortal.pl, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Entity e : EntityHandler.getEntitiesByLocation(init.lobby_joinPortal, 1.0f)) {
                    if (e instanceof Player) {
                        final Player p = (Player)e;
                        if (joinPortal.playerInPortal.contains(p)) {
                            continue;
                        }
                        joinPortal.playerInPortal.add(p);
                        ArenaMethods.join(p);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(joinPortal.pl, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                joinPortal.playerInPortal.remove(p);
                            }
                        }, 140L);
                    }
                }
            }
        }, 20L, 20L);
    }
}
