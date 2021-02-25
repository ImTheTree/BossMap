package me.darklolly0312.map.BossMap.MySQL;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQL {
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;
    
    public MySQL() {
        this.host = "localhost";
        this.port = "3306";
        this.database = "darklolly0312";
        this.username = "root";
        this.password = "";
    }
    
    public boolean isConnected() {
        return this.connection != null;
    }
    
    public void connect() throws ClassNotFoundException, SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password);
    }
    
    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}
