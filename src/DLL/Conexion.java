package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String URL = "jdbc:mysql://localhost:3306/hilos";
    private static String USER = "root";
    private static String PASSWORD = "";
    
    private static Connection conect;
    private static Conexion instance;

    private Conexion() {
        try {
            conect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar: " + e.getMessage(), e);
        }
    }

    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;    
    }

    public Connection getConnection() {
        return conect;
    }

    public void closeConnection() {
        if (conect != null) {
            try {
                conect.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
/*public class Conexion {
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            Class.forName("org.mysql.jdbc.Driver"); // Cargar el controlador de MariaDB
            String url = "jdbc:mysql://localhost:3306/hilos"; // Cambia esto por tu base de datos
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
}*/