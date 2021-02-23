package me.darklolly0312.map.BossMap;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.darklolly0312.map.BossMap.CustomEntity.Boss;
import me.darklolly0312.map.BossMap.MySQL.MySQL;
import me.darklolly0312.map.BossMap.MySQL.SQL_PlayerJoinEvent;
import me.darklolly0312.map.BossMap.MySQL.SQLGetter;
import me.darklolly0312.map.BossMap.bossSpells.napalm_bombs;
import me.darklolly0312.map.BossMap.commands.CMD_bossMap;
import me.darklolly0312.map.BossMap.commands.CMD_setClass;
import me.darklolly0312.map.BossMap.testing.NPC;
import me.darklolly0312.map.BossMap.testing.test3;
import me.darklolly0312.map.BossMap.testing.test9;
import me.darklolly0312.map.BossMap.testing.test1;
import me.darklolly0312.map.BossMap.testing.test2;

@SuppressWarnings("unused")
public class init extends JavaPlugin {
	static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
	public static MySQL SQL;
	public static SQLGetter data;
	public static boolean MySQL = false;
	
	// variables
	public static String worldName = "world";

	public static boolean isActive = false;
	public static int maxPlayers = 2; //dev 2, default 5
	public static Location lobby_playerSpawn;
	public static Location waitRoom_playerSpawn;
	public static Location lobby_joinPortal;
	public static Location arena_mid;
	public static Location arena_playerSpawn;
	public static Location arena_bossSpawn;
	public static Location arena_playerPos_1;
	public static Location arena_playerPos_2;
	public static Location arena_playerPos_3;
	public static Location arena_playerPos_4;
	public static HashMap<Player, String> playerClasses = new HashMap<Player, String>();
	public static ArrayList<Player> joinedPlayers = new ArrayList<Player>();
	
	// schedulers
	private static int particles_1;
	private static int particles_2;
	
	
	public void onDisable() {
		resetPlugin();
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		
		
		if (MySQL) {
			SQL = new MySQL();
			data = new SQLGetter(this);
			
			try {
				SQL.connect();
			} catch (ClassNotFoundException | SQLException e) {
				Bukkit.getLogger().info("MySQL-Database nicht verbunden!");
				//e.printStackTrace();
			}
			
			if (SQL.isConnected()) {
				Bukkit.getLogger().info("MySQL-Database ist verbunden.");
				data.CreateMySQLTable();
				// watch video (https://www.youtube.com/watch?v=BIww-sYzEdU) for this --> data.createTable();
			}
		}
		
		
		
		
		
		
		// locations
		lobby_playerSpawn = new Location(Bukkit.getWorld(worldName), 122.5, 62.0, -119.5); // needs fix (old)
			lobby_playerSpawn.setYaw(180);
		waitRoom_playerSpawn = new Location(Bukkit.getWorld(worldName), 124.5, 61.5, -213.5); // needs fix (old)
			waitRoom_playerSpawn.setYaw(180);
		lobby_joinPortal = new Location(Bukkit.getWorld(worldName), 122.5, 63.0, -204.5);
		arena_mid = new Location(Bukkit.getWorld(worldName), 121, 16.0, -64.5);
		arena_bossSpawn = new Location(Bukkit.getWorld(worldName), 121, 16.0, -64.5);
		arena_playerPos_1 = new Location(Bukkit.getWorld(worldName), 121.5, 16, -48.5);
		arena_playerPos_2 = new Location(Bukkit.getWorld(worldName), 105.5, 16, -64.5);
		arena_playerPos_3 = new Location(Bukkit.getWorld(worldName), 121.5, 16, -80.5);
		arena_playerPos_4 = new Location(Bukkit.getWorld(worldName), 137.5, 16, -64.5);
		
		// signTypes
		infoSigns.signTypes.add(Material.OAK_SIGN);
		infoSigns.signTypes.add(Material.OAK_WALL_SIGN);
		infoSigns.signTypes.add(Material.LEGACY_SIGN);
		infoSigns.signTypes.add(Material.LEGACY_WALL_SIGN);

		infoSigns.infoSign_berserker = new Location(Bukkit.getWorld(worldName), 108, 64, -149);
		infoSigns.infoSign_beschuetzer = new Location(Bukkit.getWorld(worldName), 108, 64, -164);
		infoSigns.infoSign_moench = new Location(Bukkit.getWorld(worldName), 136, 64, -166);
		infoSigns.infoSign_schuetze = new Location(Bukkit.getWorld(worldName), 136, 64, -151);
		infoSigns.spellsSign_berserker = new Location(Bukkit.getWorld(worldName), 108, 64, -150);
		infoSigns.spellsSign_beschuetzer = new Location(Bukkit.getWorld(worldName), 108, 64, -165);
		infoSigns.spellsSign_moench = new Location(Bukkit.getWorld(worldName), 136, 64, -165);
		infoSigns.spellsSign_schuetze = new Location(Bukkit.getWorld(worldName), 136, 64, -150);
		
		registerCommands();
		registerListeners();
		registerSchedulers();
		
		joinPortal.startPortal();
		
		System.out.println("[BossMap] geladen...");
	}



	
	
	

	private void registerSchedulers() {
		// init
		joinPortal.startPortal();
		// spells
		napalm_bombs.startTimers();
		
		// tests
		test1.startTimers();
		test3.startTimers();
		test9.startTimers();
		
		// temp's
		
		
	}

	private void registerCommands() {

		getCommand("bossmap").setExecutor(new CMD_bossMap());
		getCommand("setclass").setExecutor(new CMD_setClass());
		
	}
	
	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		
		// init and events
		manager.registerEvents(new infoSigns(), this);
		manager.registerEvents(new joinPortal(), this);
		manager.registerEvents(new NPC(), this);
		manager.registerEvents(new SQL_PlayerJoinEvent(), this);
		
		// spells
		manager.registerEvents(new napalm_bombs(), this);
		
		// tests
		manager.registerEvents(new test1(), this);
		manager.registerEvents(new test3(), this);
		manager.registerEvents(new test9(), this);
		
		// temp's

	}
	
	public static void resetPlugin() {
		// init
		
		// spells
		napalm_bombs.stopTimers();
		
		// tests
		test1.stopTimers();
		test2.stopTimer();
		test3.stopTimers();
		test9.stopTimers();
		
		// temp's
		
		// MySQL
		if (MySQL) {SQL.disconnect();}
		
		// safety
		for (Entity e : Bukkit.getWorld("world").getEntities()) {
			if (e.getName().equals("§4[§c§lBOSS§4]")) {
				e.remove();
			}
			if (e instanceof Player) {
				Player p = (Player) e;
				p.teleport(lobby_playerSpawn);
				p.sendMessage("Du wurdest zur Lobby teleportiert.");
			}
		}
	}
	
	
	

}







































































