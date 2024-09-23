import javax.swing.JOptionPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
 class Factura {
    private Cliente cliente;
    private Pedido pedido;
    private LocalDateTime fechaEmision;

    public Factura(Cliente cliente, Pedido pedido) {
        this.cliente = cliente;
        this.pedido = pedido;
        this.fechaEmision = LocalDateTime.now();
    }

    public void imprimirFactura() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yy hh:mm");
        StringBuilder factura = new StringBuilder();
        factura.append("Fecha de emision: ").append(fechaEmision.format(formatter)).append("\n");
        factura.append("Factura para: ").append(cliente.getNombre()).append("\n");
        factura.append("Detalles del pedido:\n");
        
        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue(); factura.append("- ").append(producto.getNombre()).append(" | Precio: $").append(producto.getPrecio()).append(" | Cantidad: ").append(cantidad).append("\n");
        }
        factura.append("Total: $").append(pedido.calcularTotal()).append("\n");
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Factura para: " + cliente.getNombre() +
               " | ID de Pedido: " + pedido.getId() +
               " | Fecha: " + fechaEmision.format(formatter);
    }
}
