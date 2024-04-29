package core;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class that manages the database connection
public class Db {
    private static Db instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost/turizmacentesistemi";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASS = "962610";

    // Constructor method
    private Db() {
        try {
            // Establish database connection
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        } catch (SQLException e) {
            // In case of error, print the error message to the screen
            System.out.println(e.getMessage());
        }


    }

    //Method that returns the link
    public Connection getConnection() {
        return connection;
    }

    // Method to limit database connection to a single instance with singleton pattern
    public static Connection getInstance() {

        try {
            // If the instance is null or the connection is closed
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();

            }
        } catch (SQLException e) {
            // In case of error, print the error message to the screen
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }


}
