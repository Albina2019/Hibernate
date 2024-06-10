package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static Properties getProps(){
        Properties prop = new Properties();
        try {
            prop.load(Util.class.getClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
    public static Connection connection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties props = getProps();
        try {
            return
            DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory setUp() {
        try {
            Properties properties = getProps();
            return new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory(new StandardServiceRegistryBuilder()
                            .applySetting("hibernate.connection.url", properties.getProperty("url"))
                            .applySetting("hibernate.connection.username", properties.getProperty("userName"))
                            .applySetting("hibernate.connection.password", properties.getProperty("password"))
                            .applySetting("hibernate.show_sql", properties.getProperty("hibernate.show_sql"))
                            .applySetting("hibernate.format_sql", properties.getProperty("hibernate.format_sql"))
                            .build());
        } catch (Throwable e) {
            System.out.println("Не удалось подключиться к базе");
            throw new ExceptionInInitializerError(e);
        }
    }
}
