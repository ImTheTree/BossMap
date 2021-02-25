package me.darklolly0312.map.BossMap.testing;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Effect;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class test8 {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    
    public static void use(final Player p) {
        final Location loc = p.getLocation();
        final World w = p.getWorld();
        final double radius = 0.4;
        final int n = 8;
        for (int i = 0; i < 6; ++i) {
            final double angle = 6.283185307179586 * i / n;
            final Location base = loc.clone().add(new Vector(radius * Math.cos(angle), 0.0, radius * Math.sin(angle)));
            for (int j = 0; j <= 8; ++j) {
                w.playEffect(base, Effect.SMOKE, j);
            }
        }
    }
}
