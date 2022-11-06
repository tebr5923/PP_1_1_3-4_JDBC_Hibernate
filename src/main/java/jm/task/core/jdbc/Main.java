package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());

        userService.dropUsersTable();

        userService.createUsersTable();
        // save
        User ivan = new User();
        ivan.setName("Ivan");
        ivan.setLastName("Ivanov");
        ivan.setAge((byte) 22);
        userService.saveUser(ivan.getName(), ivan.getLastName(), ivan.getAge());

        // drop table
        //userService.dropUsersTable();
    }
}
