package me.darklolly0312.map.BossMap.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darklolly0312.map.BossMap.init;

public class SQL_PlayerJoinEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (init.MySQL) {
			if (init.SQL.isConnected()) {
				SQLGetter.createPlayer(p);
				p.sendMessage("Creating player in SQL...");
			}
		}
	}
	
	
}
