import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/ResumeDB";
            String username = "root"; // Your MySQL username
            String password = "password"; // Your MySQL password

            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database!", e);
        }
    }
}
