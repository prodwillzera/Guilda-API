/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author nmare
 */
public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties props = new Properties();
                InputStream input = DatabaseConnection.class
                    .getClassLoader()
                    .getResourceAsStream("database.properties");

                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);

                System.out.println("Conexão com banco de dados estabelecida LIL BRO!");
            } catch (Exception e) {
                System.err.println("X Erro ao conectar ao banco LIL BRO: " + e.getMessage());
                throw new SQLException(e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada!");
            }
        } catch (SQLException e) {
            System.err.println("X Erro ao fechar conexão: " + e.getMessage());
        }
    }
}