import javax.swing.JOptionPane;
import java.util.Map;
public class Factura {
    private Cliente cliente;
    private Pedido pedido;

    public Factura(Cliente cliente, Pedido pedido) {
        this.cliente = cliente;
        this.pedido = pedido;
    }

    public void imprimirFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura para: ").append(cliente.getNombre()).append("\n");
        factura.append("Detalles del pedido:\n");
        
        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            factura.append("- ").append(producto.getNombre())
                    .append(" | Precio: $").append(producto.getPrecio())
                    .append(" | Cantidad: ").append(cantidad)
                    .append("\n");
        }
        factura.append("Total: $").append(pedido.calcularTotal()).append("\n");
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
    }
}
