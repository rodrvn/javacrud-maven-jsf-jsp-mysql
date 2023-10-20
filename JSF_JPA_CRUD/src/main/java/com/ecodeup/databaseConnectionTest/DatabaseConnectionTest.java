package com.ecodeup.databaseConnectionTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdf_jpa_crud";
        String username = "root";
        String password = "root";

        try {
            // Cargar el controlador JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión a la base de datos
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Si llegamos a este punto sin excepciones, la conexión fue exitosa
            System.out.println("Conexión exitosa a la base de datos MySQL.");
            
            // Cerrar la conexión
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
