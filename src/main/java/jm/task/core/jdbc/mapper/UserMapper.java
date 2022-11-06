package jm.task.core.jdbc.mapper;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAge(resultSet.getByte("age"));
        return user;
    }

    @Override
    public void map(PreparedStatement statement, User model) throws SQLException {
        statement.setString(1, model.getName());
        statement.setString(2, model.getLastName());
        statement.setByte(3, model.getAge());
        if (model.getId() != null) {
            statement.setLong(4, model.getId());
        }
    }
}

