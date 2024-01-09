package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Connection connection() {

        String database = "mailing";
        String url = "jdbc:postgresql://localhost/" + database.trim();
        String user = "postgres";
        String pass = "12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
//            System.out.println("connection to database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
