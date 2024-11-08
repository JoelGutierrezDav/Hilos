package DLL;

import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Pedido;

public class ControllerPedido {
    private Connection con;

    public ControllerPedido() {
        this.con = Conexion.getInstance().getConnection();
    }

    public void agregarPedido(Pedido pedido) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO pedido (Fecha_Pedido, Total, producto_codigo, factura_idfactura, cliente_idcliente) " +
                "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, pedido.getFechaPedido());
            statement.setDouble(2, pedido.getTotal());
            statement.setString(3, pedido.getCodigoProducto());
            statement.setInt(4, pedido.getIdFactura());
            statement.setInt(5, pedido.getIdCliente());
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null, "Pedido guardado con éxito. ID: " + id);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el pedido: " + e.getMessage());
        }
    }

    public LinkedList<Pedido> mostrarPedidos() {
        LinkedList<Pedido> pedidos = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM pedido"
            );
            ResultSet resultados = statement.executeQuery();
            
            while (resultados.next()) {
                pedidos.add(new Pedido(
                    resultados.getInt("idpedido"),
                    resultados.getString("Fecha_Pedido"),
                    resultados.getDouble("Total"),
                    resultados.getString("producto_codigo"),
                    resultados.getInt("factura_idfactura"),
                    resultados.getInt("cliente_idcliente")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public Pedido buscarPedido(int id) {
        Pedido pedido = null;
        try {
            PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM pedido WHERE idpedido = ?"); 
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            
            if (resultado.next()) {
                pedido = new Pedido(
                    resultado.getInt("idpedido"),
                    resultado.getString("Fecha_Pedido"),
                    resultado.getDouble("Total"),
                    resultado.getString("producto_codigo"),
                    resultado.getInt("factura_idfactura"),
                    resultado.getInt("cliente_idcliente")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar pedido: " + e.getMessage());
        }
        return pedido;
    }

    public boolean modificarPedido(int id, Pedido nuevoPedido) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE pedido SET Fecha_Pedido = ?, Total = ?, producto_codigo = ?, factura_idfactura = ?, cliente_idcliente = ? WHERE idpedido = ?"
            );
            statement.setString(1, nuevoPedido.getFechaPedido());
            statement.setDouble(2, nuevoPedido.getTotal());
            statement.setString(3, nuevoPedido.getCodigoProducto());
            statement.setInt(4, nuevoPedido.getIdFactura());
            statement.setInt(5, nuevoPedido.getIdCliente());
            statement.setInt(6, id);
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido actualizado con éxito");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pedido: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarPedido(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "DELETE FROM pedido WHERE idpedido = ?"
            );
            statement.setInt(1, id);
            
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido eliminado con éxito");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pedido: " + e.getMessage());
        }
        return false;
    }
}