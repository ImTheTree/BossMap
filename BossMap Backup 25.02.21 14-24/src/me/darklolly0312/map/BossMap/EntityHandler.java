package me.darklolly0312.map.BossMap;

import java.util.ArrayList;
import org.bukkit.entity.Entity;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class EntityHandler {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    
    public static List<Entity> getEntitiesByLocation(final Location loc, final float r) {
        final List<Entity> ent = new ArrayList<Entity>();
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distanceSquared(loc) <= r) {
                ent.add(e);
            }
        }
        return ent;
    }
    
    public static List<Entity> getEntitiesByFixedLocation(final Location loc, final float r) {
        final List<Entity> ent = new ArrayList<Entity>();
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distanceSquared(loc) <= r) {
                ent.add(e);
            }
        }
        return ent;
    }
}
