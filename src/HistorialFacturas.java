import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class HistorialFacturas {
    private List<Factura> facturas;

    public HistorialFacturas() {
        facturas = new ArrayList<>();
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void mostrarFacturas() {
        if (facturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay facturas registradas.");
        } else {
            StringBuilder mensaje = new StringBuilder("Facturas registradas:\n");
            for (Factura factura : facturas) {
                mensaje.append("Cliente: ").append(factura.getCliente().getNombre())
                        .append(" - Total: ").append(factura.calcularTotal()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    public void mostrarProductosMasVendidos() {
        Map<String, Integer> ventasPorProducto = new HashMap<>();

        for (Factura factura : facturas) {
            for (Producto producto : factura.getProductos()) {
                ventasPorProducto.put(
                    producto.getNombre(),
                    ventasPorProducto.getOrDefault(producto.getNombre(), 0) + 1
                );
            }
        }

        if (ventasPorProducto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han registrado ventas.");
        } else {
            StringBuilder mensaje = new StringBuilder("Productos más vendidos:\n");
            for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
                mensaje.append("Producto: ").append(entry.getKey())
                       .append(" - Ventas: ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }
}
