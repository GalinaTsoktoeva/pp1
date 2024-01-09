package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        final String testName1 = "Ivan";
        final String testLastName1 = "Ivanov";
        final byte testAge1 = 5;

        final String testName2 = "Petr";
        final String testLastName2 = "Petrov";
        final byte testAge2 = 45;

        final String testName3 = "Semen";
        final String testLastName3 = "Semenov";
        final byte testAge3 = 24;

        final String testName4 = "Sidr";
        final String testLastName4 = "Sidorov";
        final byte testAge4 = 95;

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser(testName1, testLastName1, testAge1);
        userService.saveUser(testName2, testLastName2, testAge2);
        userService.saveUser(testName3, testLastName3, testAge3);
        userService.saveUser(testName4, testLastName4, testAge4);

        List<User> usersSelect = userService.getAllUsers();
        System.out.println(usersSelect);
//        userService.removeUserById(101);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
