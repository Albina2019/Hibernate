package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Женя", "Петров", (byte) 78);
        userService.saveUser("Наталья", "Шкода", (byte) 74);
        userService.saveUser("Лина", "Разина", (byte) 59);
        userService.saveUser("Тая", "Иванова", (byte) 74);

        //userService.removeUserById(2);

        userService.getAllUsers().forEach(System.out::println);

       // userService.cleanUsersTable();

        //userService.dropUsersTable();// реализуйте алгоритм здесь
    }
}
