package me.darklolly0312.map.BossMap.MySQL;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import me.darklolly0312.map.BossMap.init;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class SQL_PlayerJoinEvent implements Listener {
	
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (init.MySQL && init.SQL.isConnected()) {
            SQLGetter.createPlayer(p);
            p.sendMessage("Creating player in SQL...");
        }
    }
}
