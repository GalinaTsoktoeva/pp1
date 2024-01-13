package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        Util util = new Util();

        try (Connection conn = util.connection()){

            statement = conn.createStatement();
            String sql = "CREATE TABLE USERS " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            int result = statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        Util util = new Util();

        try (Connection conn = util.connection()) {
            statement = conn.createStatement();
            String sql = "DROP TABLE USERS";
            int result = statement.executeUpdate(sql);
            System.out.println("Table deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Util util = new Util();

        try (Connection conn = util.connection()) {
            String sql = "INSERT INTO users(id, name, lastName, age) VALUES( ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement("" + sql);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getAge());
            int result = statement.executeUpdate();
            System.out.println("User с именем - " + user.getName() + " добавлен в базу данных!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        Util util = new Util();
        Statement statement = null;
        String sql = "DELETE FROM USERS WHERE id = "+id;
        try (Connection conn = util.connection()) {
            statement = conn.createStatement();
            int result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Util util = new Util();
        Statement statement = null;
        String sql = "SELECT * FROM USERS";
        try (Connection conn = util.connection()){
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getByte(4));
                System.out.println(user.toString());
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        Statement statement = null;
        String sql = "TRUNCATE users";
        try (Connection conn = util.connection()) {
            statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
