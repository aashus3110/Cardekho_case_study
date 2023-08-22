package com.jspiders.car_dekho_jdbc.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.jspiders.car_dekho_jdbc.operation.Operation;

public class CarMain {
    private static final String filePath = "E:\\WEJA2\\car_dekho_jdbc\\resources\\db_info.properties";

    public static void main(String[] args) {
        try {
            Connection connection = openConnection();
            if (connection != null) {
                System.out.println("Connected to the database.");
                boolean loop = true;
                while (loop) {
                    loop = Operation.carDekho(connection);
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
            closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection openConnection() {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(filePath)) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driverPath = properties.getProperty("driverPath");
        String url = properties.getProperty("dburl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        try {
            Class.forName(driverPath);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
