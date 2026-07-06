import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    public static void main(String[] args) {
        // WAMP Server ka port aur database name
        String url = "jdbc:mysql://localhost:3307/defence_comm_db"; 
        String user = "root";
        String password = ""; 

        try {
            // Translator (Driver) load karna
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Mission Accomplished! Java Backend is connected to Database.\n");

            // --- NAYA CODE: DATA FETCH KARNA ---
            
            // 1. Apni SQL Query likhein
            String query = "SELECT username, role, failed_attempts FROM users_tbl";
            Statement stmt = conn.createStatement();
            
            // 2. Query ko database mein chalayein aur result (ResultSet) laayein
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("=== SECURE MILITARY NETWORK USERS ===");
            
            // 3. Ek-ek karke saari lines (rows) print karein
            while (rs.next()) {
                String dbUser = rs.getString("username");
                String dbRole = rs.getString("role");
                int attempts = rs.getInt("failed_attempts");
                
                System.out.println("User: " + dbUser + " | Role: " + dbRole + " | Failed Logins: " + attempts);
            }
            
            // Kaam hone ke baad connection close karna good practice hai
            conn.close();

        } catch (Exception e) {
            System.out.println("Arre! Kuch gadbad ho gayi:");
            e.printStackTrace();
        }
    }
}