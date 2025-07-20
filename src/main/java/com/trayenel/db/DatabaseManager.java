package com.trayenel.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    String connectionString;

    DatabaseManager(String connectionString) {
        this.connectionString = connectionString;
        var dataSource = new MysqlDataSource();
        dataSource.setURL(connectionString);

        try {
            Connection connection = dataSource.getConnection();

            System.out.println("Successfully connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
