package me.darklolly0312.map.BossMap;

import org.bukkit.entity.Entity;
import me.darklolly0312.map.BossMap.testing.test2;
import org.bukkit.plugin.PluginManager;
import me.darklolly0312.map.BossMap.MySQL.SQL_PlayerJoinEvent;
import me.darklolly0312.map.BossMap.testing.NPC;
import me.darklolly0312.map.BossMap.commands.CMD_bossMap;
import me.darklolly0312.map.BossMap.testing.test9;
import me.darklolly0312.map.BossMap.testing.test3;
import me.darklolly0312.map.BossMap.testing.test1;
import me.darklolly0312.map.BossMap.bossSpells.napalm_bombs;
import me.darklolly0312.map.BossMap.classes.CMD_setClass;

import org.bukkit.Material;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import me.darklolly0312.map.BossMap.MySQL.SQLGetter;
import me.darklolly0312.map.BossMap.MySQL.MySQL;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.*;

public class init extends JavaPlugin {
    static Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("BossMap");
    public static MySQL SQL;
    public static SQLGetter data;
    public static boolean MySQL = false;
    public static String worldName = "world";
    public static boolean isActive = false;
    public static int maxPlayers = 2;
    public static Location lobby_playerSpawn;
    public static Location waitRoom_playerSpawn;
    public static Location lobby_joinPortal;
    public static Location lobby_friendlyDummySpawn;
    public static Location lobby_hostileDummySpawn;
    public static Location arena_mid;
    public static Location arena_playerSpawn;
    public static Location arena_bossSpawn;
    public static Location arena_playerPos_1;
    public static Location arena_playerPos_2;
    public static Location arena_playerPos_3;
    public static Location arena_playerPos_4;
    public static ArrayList<Player> joinedPlayers = new ArrayList<Player>();
    
    
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
    	
        lobby_playerSpawn = new Location(Bukkit.getWorld(init.worldName), 122.5, 62.0, -119.5);
        		lobby_playerSpawn.setYaw(180.0f);
        waitRoom_playerSpawn = new Location(Bukkit.getWorld(init.worldName), 124.5, 61.5, -213.5);
        		waitRoom_playerSpawn.setYaw(180.0f);
        lobby_joinPortal = new Location(Bukkit.getWorld(init.worldName), 122.5, 63.0, -204.5);

        lobby_friendlyDummySpawn = new Location(Bukkit.getWorld(init.worldName), 117.0, 62.5, -180.0);
        		lobby_friendlyDummySpawn.setYaw(-90.0f);
        lobby_hostileDummySpawn = new Location(Bukkit.getWorld(init.worldName), 128.0, 62.5, -180.0);
        		lobby_hostileDummySpawn.setYaw(90.0f);
        arena_mid = new Location(Bukkit.getWorld(init.worldName), 121.0, 16.0, -64.5);
        arena_bossSpawn = new Location(Bukkit.getWorld(init.worldName), 121.0, 16.0, -64.5);
        arena_playerPos_1 = new Location(Bukkit.getWorld(init.worldName), 121.5, 16.0, -48.5);
        arena_playerPos_2 = new Location(Bukkit.getWorld(init.worldName), 105.5, 16.0, -64.5);
        arena_playerPos_3 = new Location(Bukkit.getWorld(init.worldName), 121.5, 16.0, -80.5);
        arena_playerPos_4 = new Location(Bukkit.getWorld(init.worldName), 137.5, 16.0, -64.5);
        infoSigns.signTypes.add(Material.OAK_SIGN);
        infoSigns.signTypes.add(Material.OAK_WALL_SIGN);
        infoSigns.signTypes.add(Material.LEGACY_SIGN);
        infoSigns.signTypes.add(Material.LEGACY_WALL_SIGN);
        infoSigns.infoSign_berserker = new Location(Bukkit.getWorld(init.worldName), 108.0, 64.0, -149.0);
        infoSigns.infoSign_beschuetzer = new Location(Bukkit.getWorld(init.worldName), 108.0, 64.0, -164.0);
        infoSigns.infoSign_moench = new Location(Bukkit.getWorld(init.worldName), 136.0, 64.0, -166.0);
        infoSigns.infoSign_schuetze = new Location(Bukkit.getWorld(init.worldName), 136.0, 64.0, -151.0);
        infoSigns.spellsSign_berserker = new Location(Bukkit.getWorld(init.worldName), 108.0, 64.0, -150.0);
        infoSigns.spellsSign_beschuetzer = new Location(Bukkit.getWorld(init.worldName), 108.0, 64.0, -165.0);
        infoSigns.spellsSign_moench = new Location(Bukkit.getWorld(init.worldName), 136.0, 64.0, -165.0);
        infoSigns.spellsSign_schuetze = new Location(Bukkit.getWorld(init.worldName), 136.0, 64.0, -150.0);
        this.registerCommands();
        this.registerListeners();
        this.registerSchedulers();
        joinPortal.startPortal();
        System.out.println("[BossMap] geladen...");
    }
    
    private void registerSchedulers() {
        joinPortal.startPortal();
        Lobby.startTimers();
        napalm_bombs.startTimers();
        test1.startTimers();
        test3.startTimers();
        test9.startTimers();
    }
    
    private void registerCommands() {
        this.getCommand("bossmap").setExecutor(new CMD_bossMap());
        this.getCommand("setclass").setExecutor(new CMD_setClass());
    }
    
    private void registerListeners() {
        final PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new infoSigns(), this);
        manager.registerEvents(new joinPortal(), this);
        manager.registerEvents(new NPC(), this);
        manager.registerEvents(new Lobby(), this);
        manager.registerEvents(new SQL_PlayerJoinEvent(), this);
        manager.registerEvents(new napalm_bombs(), this);
        manager.registerEvents(new test1(), this);
        manager.registerEvents(new test3(), this);
        manager.registerEvents(new test9(), this);
    }
    
    public static void resetPlugin() {
        Lobby.stopTimers();
        napalm_bombs.stopTimers();
        test1.stopTimers();
        test2.stopTimer();
        test3.stopTimers();
        test9.stopTimers();
        
        Lobby.playerOnWorld = false;
        
        if (init.MySQL) {
            init.SQL.disconnect();
        }
        for (final Entity e : Bukkit.getWorld("world").getEntities()) {
            if (e.getName().equals("§4[§c§lBOSS§4]")) {
                e.remove();
            }
            if (e.getName().contains("§aFreundliche Kampfattrappe [§2§lHP: ")) {
                e.remove();
                Bukkit.broadcastMessage("dev dummy removed");
            }
            if (e.getName().contains("§cFeindliche Kampfattrappe [§2§lHP: ")) {
                e.remove();
                Bukkit.broadcastMessage("dev hdummy removed");
            }
            if (e instanceof Player) {
                final Player p = (Player)e;
                p.teleport(init.lobby_playerSpawn);
                p.sendMessage("Du wurdest zur Lobby teleportiert.");
            }
        }
    }
}
