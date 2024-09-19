import javax.swing.JOptionPane;

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
        for (Producto producto : pedido.getProductos()) {
            factura.append("- ").append(producto.getNombre()).append(" | Precio: $").append(producto.getPrecio()).append(" | Cantidad: ").append(producto.getStock()).append("\n");
            producto.reducirStock(1);
        }
        factura.append("Total: $").append(pedido.calcularTotal()).append("\n");
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
    }
}
