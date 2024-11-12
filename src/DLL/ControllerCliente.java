package DLL;

import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Cliente;
import DLL.Conexion;

public class ControllerCliente {
    private Connection con;

    public ControllerCliente() {
        this.con = Conexion.getInstance().getConnection();
    }

    public void crearCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO cliente (Nombre) VALUES (?)", 
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, cliente.getNombre());
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null, "Cliente guardado con éxito. ID: " + id);
                }
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
                clientes.add(new Cliente(
                    resultados.getInt("idcliente"),
                    resultados.getString("Nombre")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM cliente WHERE idcliente = ?"
            );
            statement.setInt(1, id);
            
            ResultSet resultado = statement.executeQuery();
            
            if (resultado.next()) {
                cliente = new Cliente(
                    resultado.getInt("idcliente"),
                    resultado.getString("Nombre")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public boolean actualizarCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE cliente SET Nombre = ? WHERE idcliente = ?"
            );
            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getId());
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarCliente(int id) {
        try {
            eliminarFacturasYPedidosDeCliente(id);
            
            PreparedStatement statement = con.prepareStatement(
                "DELETE FROM cliente WHERE idcliente = ?"
            );
            statement.setInt(1, id);
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
        }
        return false;
    }

    private void eliminarFacturasYPedidosDeCliente(int clienteId) throws SQLException {
        PreparedStatement statementPedidos = con.prepareStatement(
            "DELETE FROM pedido WHERE cliente_idcliente = ?"
        );
        statementPedidos.setInt(1, clienteId);
        statementPedidos.executeUpdate();

        PreparedStatement statementFacturas = con.prepareStatement(
            "DELETE FROM factura WHERE cliente_idcliente = ?"
        );
        statementFacturas.setInt(1, clienteId);
        statementFacturas.executeUpdate();
    }
}