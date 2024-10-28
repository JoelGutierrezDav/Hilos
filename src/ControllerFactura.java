import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerFactura {
    private Factura factura; 
    private Inventario inventario; 
    private ControllerCliente controllerCliente; 
    private ControllerPedido controllerPedido; 
    private Connection con; 

    public ControllerFactura(Inventario inventario, ControllerCliente controllerCliente, ControllerPedido controllerPedido) {
        this.inventario = inventario;
        this.controllerCliente = controllerCliente;
        this.controllerPedido = controllerPedido;
        this.con = Conexion.getInstance().getConnection(); 
    }

    public void crearFactura(Cliente cliente, Pedido pedido) {
        this.factura = new Factura(cliente, pedido);
        guardarFacturaEnBaseDatos();
        imprimirFactura();
    }

    private void guardarFacturaEnBaseDatos() {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO factura (Fecha, Total, cliente_idcliente) VALUES (?, ?, ?)"
            );
            statement.setDate(1, java.sql.Date.valueOf(factura.getFechaEmision().toLocalDate()));
            statement.setDouble(2, factura.getTotal());
            statement.setInt(3, factura.getCliente().getId());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Factura guardada en la base de datos.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura: " + e.getMessage());
        }
    }

    public void imprimirFactura() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder facturaTexto = new StringBuilder();
        facturaTexto.append("Fecha de emisión: ").append(factura.getFechaEmision().format(formatter)).append("\n");
        facturaTexto.append("Factura para: ").append(factura.getCliente().getNombre()).append("\n");
        facturaTexto.append("Detalles del pedido:\n");

        for (Map.Entry<Producto, Integer> entry : factura.getPedido().getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            facturaTexto.append("- ").append(producto.getNombre())
                        .append(" | Precio: $").append(producto.getPrecio())
                        .append(" | Cantidad: ").append(cantidad).append("\n");
        }
        facturaTexto.append("Total: $").append(factura.calcularTotal()).append("\n");

        JOptionPane.showMessageDialog(null, facturaTexto.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Factura> mostrarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM factura");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt("idfactura");
                LocalDateTime fecha = resultados.getTimestamp("Fecha").toLocalDateTime();
                double total = resultados.getDouble("Total");
                int clienteId = resultados.getInt("cliente_idcliente");
                Cliente cliente = controllerCliente.buscarCliente(clienteId); 
                facturas.add(new Factura(cliente, total, fecha, id));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar facturas: " + e.getMessage());
        }
        return facturas;
    }

    public void modificarFactura(Factura factura) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE factura SET Fecha = ?, Total = ? WHERE idfactura = ?"
            );
            statement.setDate(1, java.sql.Date.valueOf(factura.getFechaEmision().toLocalDate()));
            statement.setDouble(2, factura.getTotal());
            statement.setInt(3, factura.getId());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Factura modificada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la factura.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la factura: " + e.getMessage());
        }
    }

    public void eliminarFactura(int idFactura) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM factura WHERE idfactura = ?");
            statement.setInt(1, idFactura);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Factura eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la factura.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la factura: " + e.getMessage());
        }
    }

    public Factura buscarFacturaPorId(int idFactura) {
        Factura facturaBuscada = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM factura WHERE idfactura = ?");
            statement.setInt(1, idFactura);
            ResultSet resultados = statement.executeQuery();
            if (resultados.next()) {
                int id = resultados.getInt("idfactura");
                LocalDateTime fecha = resultados.getTimestamp("Fecha").toLocalDateTime();
                double total = resultados.getDouble("Total");
                int clienteId = resultados.getInt("cliente_idcliente");
                Cliente cliente = controllerCliente.buscarCliente(clienteId); 
                facturaBuscada = new Factura(cliente, total, fecha, id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar la factura: " + e.getMessage());
        }
        return facturaBuscada;
    }

    public Factura getFactura() {
        return factura;
    }
}

