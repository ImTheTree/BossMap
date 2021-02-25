package me.darklolly0312.map.BossMap.MySQL;

import java.sql.ResultSet;
import java.util.UUID;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import me.darklolly0312.map.BossMap.init;

public class SQLGetter {
    public SQLGetter(final init plugin) {
    }
    
    public void CreateMySQLTable() {
        try {
            final PreparedStatement ps = init.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata(NAME VARCHAR(100), UUID VARCHAR(100), MAXHEALTH DECIMAL(65), PRIMARY KEY (NAME))");
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("deprecation")
	public static void createPlayer(final Player p) {
        try {
            final UUID uuid = p.getUniqueId();
            if (!exists(uuid)) {
                final PreparedStatement ps = init.SQL.getConnection().prepareStatement("INSERT IGNORE INTO playerdata (NAME,UUID,MAXHEALTH) VALUES (?,?,?)");
                ps.setString(1, p.getName());
                ps.setString(2, p.getUniqueId().toString());
                ps.setDouble(3, p.getMaxHealth());
                ps.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean exists(final UUID uuid) {
        try {
            final PreparedStatement ps = init.SQL.getConnection().prepareStatement("SELECT * FROM playerdata WHERE UUID=?");
            ps.setString(1, uuid.toString());
            final ResultSet results = ps.executeQuery();
            return results.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void setMaxHealth(final UUID uuid, final double health) {
        try {
            final PreparedStatement ps = init.SQL.getConnection().prepareStatement("UPDATE playerdata SET MAXHEALTH=? WHERE UUID=?");
            ps.setDouble(1, health);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public double getMaxHealth(final UUID uuid) {
        try {
            final PreparedStatement ps = init.SQL.getConnection().prepareStatement("SELECT MAXHEALTH FROM playerdata WHERE UUID=?");
            ps.setString(1, uuid.toString());
            final ResultSet rs = ps.executeQuery();
            double maxHealth = 0.0;
            if (rs.next()) {
                maxHealth = rs.getInt("MAXHEALTH");
                return maxHealth;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public void deletePlayer(final UUID uuid) {
        try {
            final PreparedStatement ps = init.SQL.getConnection().prepareStatement("DELETE FROM playerdata WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
