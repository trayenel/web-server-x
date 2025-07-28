package com.trayenel.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.trayenel.base.Configurable;
import com.trayenel.base.Manager;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager implements Configurable, Manager {
    Map<String, Object> config;
    Connection connection;

    @Override
    public String start() {
        String connectionString = this.config.get("Url").toString();
        String username = this.config.get("User").toString();
        String password = this.config.get("Pass").toString();

        var dataSource = new MysqlDataSource();
        dataSource.setURL(connectionString);

        try {
            this.connection = dataSource.getConnection(username, password);

            return "Successfully connected to database";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadConfig(Map<String, Object> config) {
        this.config = config;
    }

    @Override
    public void getStatus() {
        System.out.println("Database Manager connected to " + this.config.get("Url").toString());
    }

    public Map<String, Object> selectById(int id, String table) throws SQLException {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            Map<String, Object> result = new HashMap<>();

            ResultSetMetaData metaData = rs.getMetaData();

            for (int i = 1; i < metaData.getColumnCount(); i++) {
                result.put(metaData.getColumnName(i), rs.getObject(i));
            }

            return result;
        }

        return null;
    }

    public int deleteById(int id, String table) throws SQLException {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        if (statement.executeUpdate() == 1) {
            return 200;
        } else {
            return 404;
        }
    }

    public int insert(String table, Map<String, Object> data) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO " + table + " (");

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            query.append(entry.getKey()).append(", ");
        }

        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");

        for (int i = 0; i < data.size(); i++) {
           query.append("?, ");
        }

        query.delete(query.length() - 2, query.length());
        query.append(")");

        PreparedStatement statement = connection.prepareStatement(query.toString());
        int i = 1;

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            statement.setObject(i++, entry.getValue());
        }

        if (statement.executeUpdate() == 1) {
            return 200;
        } else {
            return 404;
        }
    }
}