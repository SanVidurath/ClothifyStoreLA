package db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/databasename";
        String user = "username";
        String password = "password";
        connection = DriverManager.getConnection(connectionUrl,user,password);
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance==null){
            instance=new DBConnection();
        }
        return instance;
    }
}
