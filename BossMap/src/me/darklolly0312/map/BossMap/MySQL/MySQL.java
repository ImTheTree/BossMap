package me.darklolly0312.map.BossMap.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	private String host = "localhost";
	private String port = "3306";
	private String database = "darklolly0312";
	private String username = "root";
	private String password = "";
	
	private Connection connection;
	
	public boolean isConnected() {
		return (connection == null ? false : true);
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(	"jdbc:mysql://" +
			     									host + ":" + port + "/" + database + "?useSSL=false",
			     									username, password
			     								);
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
