package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void execute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver_class"));
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void createTable(String tableName) throws SQLException {
        execute(String.format("CREATE TABLE IF NOT EXISTS %s();", tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        execute(String.format("DROP TABLE IF EXISTS %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        execute(String.format("ALTER TABLE %s ADD %s %s NULL;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        execute(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        execute(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        String tableName = "new_table";
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "age", "INTEGER");
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.dropTable(tableName);
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "count", "INTEGER");
        tableEditor.addColumn(tableName, "email", "CHARACTER VARYING(30)");
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.renameColumn(tableName, "email", "email_add");
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.dropColumn(tableName, "email_add");
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.close();
    }

}