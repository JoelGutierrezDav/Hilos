import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	private static final String URL = "jdbc:mysql://localhost:3306/hilos";
	private static final String User = "root";
	private static final String Pass= "";
	
	private static Conexion instance;
	private Connection con;
	private Conexion() {
		try {
			con = DriverManager.getConnection(URL,User,Pass);
			JOptionPane.showMessageDialog(null, "Se conecto");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se conecto");
		}
	}
	public static Conexion getInstance() {
		if(instance==null) {
			instance = new Conexion();
		}
		return instance;
	}
	public Connection getConnection() {
		return con;
	}
}
