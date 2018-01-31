package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Activities_BDD {

	Connection conn= null;
	String url = "jdbc:sqlite:/home/thomas/Téléchargements/Activities_tracker_BDD";
	public Activities_BDD(String url) {

	}
	private void connect() {
		
		try {
			conn = DriverManager.getConnection(url);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Session> getSessionsof(int ID){
		connect();
		List<Session> resultlist= new ArrayList<Session>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE user_id="+ID+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(resultlist.get(0).getDate()+resultlist.get(1).getDate());
		disconnect();
		return resultlist;
	
	}
	
	public List<Session> getActivitiesByIdof(int ID, int ida){
		connect();
		List<Session> resultlist= new ArrayList<Session>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE user_id="+ID+" and activity_id="+ida+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(resultlist.get(0).getDate());
		disconnect();
		return resultlist;
	
	}
	
	public List<Session> getAllSessionsof( int ida){
		connect();
		List<Session> resultlist= new ArrayList<Session>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Sessions WHERE activity_id="+ida+";");
				while(resultat.next()){
					resultlist.add(new Session(resultat.getInt("id"),resultat.getInt("user_id"),resultat.getInt("activity_id"),resultat.getString("date")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(resultlist.get(0).getDate());
		disconnect();
		return resultlist;
	
	}
	
	
	public List<User> getAllUsers(){
		connect();
		List<User> resultlist= new ArrayList<User>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Users ;");
				while(resultat.next()){
					resultlist.add(new User(resultat.getInt("id"),resultat.getString("pseudo"),resultat.getString("firstname"),resultat.getString("lastname")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(User u:resultlist){
				System.out.println(u.getId());
			}
			
		disconnect();
		return resultlist;
	}
	
	private void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void createUser(String string, String string2, String string3) {
		connect();
		
		try {
			Statement statement =conn.createStatement();
			ResultSet resultat=statement.executeQuery("INSERT INTO Users(pseudo,firstname,lastname) values('"+string+"','"+string2+"','"+string3+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Activity> getAllActivities() {
		connect();
		List<Activity> resultlist= new ArrayList<Activity>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Activites ;");
				while(resultat.next()){
					resultlist.add(new Activity(resultat.getInt("id"),resultat.getString("name")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(resultlist.get(0).getName());
		disconnect();
		return resultlist;
		
	}
	public Activity getActivity(int i) {
		connect();
		Activity result=null;
		try {
			Statement statement =conn.createStatement();
			ResultSet resultat =statement.executeQuery("SELECT Name FROM Activites where id ="+i+";");
			
			result= new Activity(i,resultat.getString("Name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.getName());
		disconnect();
		return null;
			
	}
	public List<Property> getPropertiesOfActivities(int i) {
		connect();
		List<Property> resultlist= new ArrayList<Property>();
			try {
				Statement statement =conn.createStatement();
				ResultSet resultat =statement.executeQuery("SELECT * FROM Properties WHERE activity_id="+i+";");
				while(resultat.next()){
					resultlist.add(new Property(resultat.getInt("id"),resultat.getInt("activity_id"),resultat.getString("name"),Value_type.valueOf(resultat.getString("value_type"))));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(resultlist.get(0).getName());
		disconnect();
		return resultlist;
		
	}
}