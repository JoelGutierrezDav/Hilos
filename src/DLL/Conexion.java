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
            String url = "jdbc:mariadb://localhost:3306/tu_nombre_de_base_de_datos"; // Cambia esto por tu base de datos
            conexion = DriverManager.getConnection(url); // No se pasan usuario ni contraseña
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