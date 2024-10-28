import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControllerPedido {
    private static Connection con = Conexion.getInstance().getConnection();
    private ArrayList<Pedido> pedidos;

    public ControllerPedido() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        try {
            // Agregar a la lista
            pedidos.add(pedido);

            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO pedido (Fecha_Pedido, Total, producto_idproducto, factura_idfactura, cliente_idcliente) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, pedido.getFechaPedido());
            statement.setDouble(2, pedido.getTotal());
            statement.setString(3, pedido.getCodigoProducto());
            statement.setInt(4, pedido.getIdFactura());
            statement.setInt(5, pedido.getIdCliente());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido agregado exitosamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el pedido: " + e.getMessage());
        }
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : pedidos) {
            sb.append(pedido.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void modificarPedido(int idPedido, Pedido nuevoPedido) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE pedido SET Fecha_Pedido = ?, Total = ?, producto_idproducto = ?, factura_idfactura = ?, cliente_idcliente = ? WHERE idpedido = ?"
            );
            statement.setString(1, nuevoPedido.getFechaPedido());
            statement.setDouble(2, nuevoPedido.getTotal());
            statement.setString(3, nuevoPedido.getCodigoProducto());
            statement.setInt(4, nuevoPedido.getIdFactura());
            statement.setInt(5, nuevoPedido.getIdCliente());
            statement.setInt(6, idPedido);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido modificado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pedido con ID: " + idPedido);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el pedido: " + e.getMessage());
        }
    }
    public void eliminarPedido(int idPedido) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM pedido WHERE idpedido = ?");
            statement.setInt(1, idPedido);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido eliminado exitosamente.");
                pedidos.removeIf(p -> p.getIdPedido() == idPedido);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pedido con ID: " + idPedido);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido: " + e.getMessage());
        }
    }
    public Pedido buscarPedido(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }
}
