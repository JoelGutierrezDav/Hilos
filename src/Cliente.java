import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

class Cliente {
    private String nombre;
    private int id;
    private static Connection con = (Connection) Conexion.getInstance().getConnection();

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }

    public static void crearCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO `cliente`(`nombre`) VALUES (?)");
            statement.setString(1, cliente.getNombre());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente guardado con éxito!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente!");
        }
    }

    public static LinkedList<Cliente> mostrarClientes() {
        LinkedList<Cliente> clientes = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `cliente`");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                clientes.add(new Cliente(resultados.getInt("id"), resultados.getString("nombre")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los clientes!");
        }
        return clientes;
    }

    public static boolean actualizarCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE `cliente` SET `nombre`= ? WHERE id = ?");
            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getId());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente!");
        }
        return false;
    }

    public static Cliente buscarCliente(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `cliente` WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                cliente = new Cliente(resultados.getInt("id"), resultados.getString("nombre"));
            }
            return cliente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente!");
        }
        return cliente;
    }

    public static boolean eliminarCliente(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `cliente` WHERE id = ?");
            statement.setInt(1, id);

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente!");
        }
        return false;
    }
}
