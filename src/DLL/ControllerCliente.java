package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import BLL.Cliente;

public class ControllerCliente {
    private Connection con = Conexion.getInstance().getConnection();

    public void crearCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO cliente (Nombre) VALUES (?)");
            statement.setString(1, cliente.getNombre());
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente guardado con éxito!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + e.getMessage());
        }
    }

    public LinkedList<Cliente> mostrarClientes() {
        LinkedList<Cliente> clientes = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM cliente");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                clientes.add(new Cliente(resultados.getInt("idcliente"), resultados.getString("Nombre")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los clientes: " + e.getMessage());
        }
        return clientes;
    }

    public boolean actualizarCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE cliente SET Nombre = ? WHERE idcliente = ?");
            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getId());
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.getMessage());
        }
        return false;
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM cliente WHERE idcliente = ?");
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();
            if (resultados.next()) {
                cliente = new Cliente(resultados.getInt("idcliente"), resultados.getString("Nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente: " + e.getMessage());
        }
        return cliente;
    }

    public boolean eliminarCliente(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            statement.setInt(1, id);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage());
        }
        return false;
    }
}
