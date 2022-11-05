package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        try {
           // Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task_1_1_4",
                    "root",
                    "root"
            );
            System.out.println("Connection OK");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection Error");
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
