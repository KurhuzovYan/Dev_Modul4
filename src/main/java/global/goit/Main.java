package global.goit;

import global.goit.controller.Database;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Connection connection = Database.getConnection();

    }
}
