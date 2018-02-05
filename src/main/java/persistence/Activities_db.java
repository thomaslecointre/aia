package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Activities_db {

	static Connection conn= null;
	static String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/../lib/databases/Activities_tracker_db";
	public Activities_db(String url) {

	}
	private static void connect() throws SQLException {

			conn = DriverManager.getConnection(url);
	}
	public static List<Session> getSessionsof(int ID) throws SQLException {
		connect();
		List<Session> resultlist= new ArrayList<Session>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE user_id="+ID+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}
		disconnect();
		return resultlist;
	
	}
	
	public static List<Session> getActivitiesByIdof(int ID, int ida) throws SQLException {
		connect();
		List<Session> resultlist= new ArrayList<Session>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE user_id="+ID+" and activity_id="+ida+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}
		disconnect();
		return resultlist;
	
	}
	
	public static List<Session> getAllSessionsof( int ida) throws SQLException {
		connect();
		List<Session> resultlist= new ArrayList<Session>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE activity_id="+ida+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}

		disconnect();
		return resultlist;
	
	}
	
	
	public static List<User> getAllUsers() throws SQLException {
		connect();
		List<User> resultlist= new ArrayList<User>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Users ;");
				while(resultat.next()){
					resultlist.add(new User(resultat.getInt("id"),resultat.getString("pseudo"),resultat.getString("firstname"),resultat.getString("lastname")));
				}
		disconnect();
		return resultlist;
	}
	
	private static void disconnect() throws SQLException {
			conn.close();
		
	}
	public static void createUser(String string, String string2, String string3) throws SQLException {
		connect();
			Statement statement =conn.createStatement();
			ResultSet resultat=statement.executeQuery("INSERT INTO Users(pseudo,firstname,lastname) values('"+string+"','"+string2+"','"+string3+"');");
		disconnect();
	}
	public static List<Activity> getAllActivities() throws SQLException {
		connect();
		List<Activity> resultlist= new ArrayList<Activity>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Activites ;");
				while(resultat.next()){
					resultlist.add(new Activity(resultat.getInt("id"),resultat.getString("name")));
				}
			System.out.println(resultlist.get(0).getName());
		disconnect();
		return resultlist;
		
	}
	public static Activity getActivity(int i) throws SQLException {
		connect();
		Activity result=null;
			Statement statement =conn.createStatement();
			ResultSet resultat =statement.executeQuery("SELECT Name FROM Activites where id ="+i+";");
			result= new Activity(i,resultat.getString("Name"));
		System.out.println(result.getName());
		disconnect();
		return null;
			
	}
	public static List<Property> getPropertiesOfActivities(int i) throws SQLException {
		connect();
		List<Property> resultlist= new ArrayList<Property>();
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Properties WHERE activity_id="+i+";");
				while(resultat.next()){
					resultlist.add(new Property(resultat.getInt("id"),resultat.getInt("activity_id"),resultat.getString("name"),Value_type.valueOf(resultat.getString("value_type"))));
				}
			System.out.println(resultlist.get(0).getName());
		disconnect();
		return resultlist;
		
	}
	public static void removeActivityOf(String id) throws SQLException {
		connect();
			Statement statement =conn.createStatement();
			ResultSet resultat=statement.executeQuery("remove from activites where id="+id+";");

		disconnect();
	}

	public static void removeSessionOf(String id,String userid ) throws SQLException {
		connect();
			Statement statement =conn.createStatement();
			ResultSet resultat=statement.executeQuery("remove from sessions where id="+id+"and user_id="+userid+" ;");

		disconnect();
	}

	public static User getUser(String id) throws SQLException {
		connect();
		User result=null;
			Statement statement =conn.createStatement();
			ResultSet resultat =statement.executeQuery("SELECT * FROM Users where id="+id+";");
				result=new User(resultat.getInt("id"),resultat.getString("pseudo"),resultat.getString("firstname"),resultat.getString("lastname"));


		disconnect();
		return result;

	}
}
