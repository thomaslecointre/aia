package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentification_db {

	Connection conn= null;
	String url= "jdbc:sqlite:" + System.getProperty("user.dir") + "/../lib/databases/Authentification_db";
	
	public Authentification_db(String url) {
		connect();
	}
	private void connect() {
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LogUser getLogUser(String username,String password){
		LogUser result = null;
		connect();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat = statement.executeQuery("SELECT * FROM Account WHERE username='"+username+"' and password='"+password+"' ;");
				while(resultat.next()){
					result=(new LogUser(resultat.getString("username"),resultat.getString("password"),resultat.getString("role")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		disconnect();
		
		
		
		
		return result;
		
	}
	private void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
