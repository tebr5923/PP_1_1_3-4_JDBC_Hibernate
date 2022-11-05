package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String script = """
                                CREATE TABLE user
                                (
                                    id   bigint        NOT NULL PRIMARY KEY,
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
            throw new IllegalStateException("NOT creat table user", e);
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
            throw new IllegalStateException("NOT drop table user", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
