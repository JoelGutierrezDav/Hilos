package BLL;
import java.time.LocalDate;

public class Factura {
    private int id;
    private LocalDate fecha;
    private double total;
    private int clienteId;


    public Factura(int id, LocalDate fecha, double total, int clienteId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.clienteId = clienteId;
    }


    public Factura(LocalDate fecha, double total, int clienteId) {
        this.fecha = fecha;
        this.total = total;
        this.clienteId = clienteId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                ", clienteId=" + clienteId +
                '}';
    }
}

    /*public void imprimirFactura() {
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
               " | Fecha: " + fechaEmision.format(formatter);*/
    

