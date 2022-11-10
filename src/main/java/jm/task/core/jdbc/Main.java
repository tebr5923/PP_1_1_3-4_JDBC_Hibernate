package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());

        userService.createUsersTable();
        // save
        User ivan = new User();
        ivan.setName("Ivan");
        ivan.setLastName("Ivanov");
        ivan.setAge((byte) 22);
        userService.saveUser(ivan.getName(), ivan.getLastName(), ivan.getAge());
        userService.saveUser("Petr", "Petrov", (byte) 23);
        userService.saveUser("Anton", "Antonov", (byte) 33);
        userService.saveUser("Andrey", "Andreev", (byte) 43);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(1);

        userService.cleanUsersTable();

        System.out.println(userService.getAllUsers());
        //drop table
        userService.dropUsersTable();
    }
}
