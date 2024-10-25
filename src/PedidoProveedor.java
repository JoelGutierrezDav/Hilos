import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

class PedidoProveedor {
    private int id;
    private String descripcion;
    private String proveedor;
    private static Connection con = (Connection) Conexion.getInstance().getConnection();

    public PedidoProveedor(int id, String descripcion, String proveedor) {
        this.id = id;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public PedidoProveedor(String descripcion, String proveedor) {
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Descripción: " + descripcion + " | Proveedor: " + proveedor;
    }

    public static void crearPedidoProveedor(PedidoProveedor pedido) {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO `PedidoProveedor`(`descripcion`, `proveedor`) VALUES (?,?)");
            statement.setString(1, pedido.getDescripcion());
            statement.setString(2, pedido.getProveedor());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Pedido de proveedor guardado con éxito!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el pedido del proveedor!");
        }
    }

    public static LinkedList<PedidoProveedor> mostrarPedidoProveedor() {
        LinkedList<PedidoProveedor> pedidos = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `PedidoProveedor`");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                pedidos.add(new PedidoProveedor(resultados.getInt("id"),
                        resultados.getString("descripcion"),
                        resultados.getString("proveedor")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los pedidos!");
        }
        return pedidos;
    }

    public static boolean actualizarPedidoProveedor(PedidoProveedor pedido) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE `PedidoProveedor` SET `descripcion`= ?, `proveedor`= ? WHERE id = ?");
            statement.setString(1, pedido.getDescripcion());
            statement.setString(2, pedido.getProveedor());
            statement.setInt(3, pedido.getId());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Pedido actualizado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el pedido del proveedor!");
        }
        return false;
    }

    public static PedidoProveedor buscarPedidoProveedor(int id) {
        PedidoProveedor pedido = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `PedidoProveedor` WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                pedido = new PedidoProveedor(resultados.getInt("id"),
                        resultados.getString("descripcion"),
                        resultados.getString("proveedor"));
            }
            return pedido;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el pedido del proveedor!");
        }
        return pedido;
    }

    public static boolean eliminarPedidoProveedor(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `PedidoProveedor` WHERE id = ?");
            statement.setInt(1, id);

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Pedido de proveedor eliminado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido del proveedor!");
        }
        return false;
    }
}
