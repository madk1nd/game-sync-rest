package ru.goodgame.model;

import java.sql.*;

public class MySQLConnection implements DatabaseConnection {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:32768/game?" +
                            "user=root&password=admin");

            // Do something with the Connection
            String query = "SELECT country FROM users";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String money = rs.getString("country");
                System.out.println(money);
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
