package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.mapper.UserMapper;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final UserMapper USER_MAPPER = new UserMapper();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String script = """
                                CREATE TABLE user
                                (
                                    id   bigint        NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    name varchar(255) NOT NULL,
                                    last_name  varchar(255) NOT NULL,
                                    age tinyint NOT NULL
                                );
                """;
        try (final Connection connection = Util.getConnection();
             final Statement statement = connection.createStatement()) {
            statement.execute(script);
            System.out.println("creat table user");
        } catch (SQLException e) {
            System.err.println("NOT creat table user");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String script = "DROP TABLE IF EXISTS user;";
        try (final Connection connection = Util.getConnection();
             final Statement statement = connection.createStatement()) {
            statement.execute(script);
            System.out.println("drop table user");
        } catch (SQLException e) {
            System.err.println("NOT drop table user");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (id, name, last_name, age) values(DEFAULT,?,?,?);";
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try (final Connection connection = Util.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            USER_MAPPER.map(statement, user);
            statement.executeUpdate();
            System.out.println("SAVE OK... user with lastname " + lastName);
        } catch (SQLException e) {
            System.err.println("cant save user");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id=?;";
        try (final Connection connection = Util.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                System.err.println("FAIL DELETE user with id " + id);
            }
            System.out.println("DELETE OK... user with id " + id);
        } catch (SQLException e) {
            System.err.println("cant delete user with id " + id);
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> courseList = new ArrayList<>();
        String sql = "select id, name, last_name, age from user;";
        try (final Connection connection = Util.getConnection();
             final ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                User user = USER_MAPPER.map(resultSet);
                courseList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("cant get all users");
            e.printStackTrace();
        }
        System.out.println("GET ALL users OK...");
        return courseList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user;";
        try (final Connection connection = Util.getConnection();
             final Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("clean table Users OK");
        } catch (SQLException e) {
            System.err.println("NOT clean table Users");
            e.printStackTrace();
        }
    }
}
