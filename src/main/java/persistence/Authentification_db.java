package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentification_db {

    static Connection conn = null;
    static String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/../lib/databases/Authentification_db";

    public Authentification_db(String url) {
    }

    private static void connect() throws SQLException {

        conn = DriverManager.getConnection(url);
    }

    public static LogUser getLogUser(String username, String password) throws SQLException {
        LogUser result = null;
        connect();
        Statement statement = conn.createStatement();
        ResultSet resultat = statement.executeQuery("SELECT * FROM Account WHERE username='" + username + "' and password='" + password + "' ;");
        while (resultat.next()) {
            result = (new LogUser(resultat.getString("username"), resultat.getString("password"), resultat.getString("role")));
        }
        disconnect();
        return result;
    }

    private static void disconnect() throws SQLException {
        conn.close();
    }

    public static boolean checkRole(String username, String role) throws SQLException {
        boolean result = false;
        connect();
        Statement statement = conn.createStatement();
        ResultSet resultat = statement.executeQuery("SELECT * FROM Account WHERE username='" + username +"' ;");
        disconnect();
        resultat.next();
        result=resultat.getString("role").equals(role);

        return result;

    }
}
