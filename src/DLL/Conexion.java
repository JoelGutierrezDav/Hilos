package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // Cargar el controlador de MariaDB
            String url = "jdbc:mariadb://localhost:3306/hilos";
            String usuario = "user"; // Cambia esto por tu usuario
            String contraseña = "pass"; // Cambia esto por tu contraseña
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver no encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConnection() {
        return conexion;
    }
}