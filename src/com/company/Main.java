package com.company;


import javax.swing.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection connection;

        try {
            connection = connectToDatabase();
            System.out.println("connected to DB");
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych");
            return;
        }

        JFrame window = new JFrame("System zarządzania Jedi");

        window.add(new Window(connection));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
    }

    private static Connection connectToDatabase() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/star_wars", "postgres",
                "kamykpatyk622");
        return connection;
    }
}



