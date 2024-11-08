package BLL;

public class Pedido {
    private int idPedido;
    private String fechaPedido;
    private double total;
    private String codigoProducto;
    private int idFactura;
    private int idCliente;

    public Pedido(String fechaPedido, double total, String codigoProducto, int idFactura, int idCliente) {
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.codigoProducto = codigoProducto;
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public Pedido(int idPedido, String fechaPedido, double total, String codigoProducto, int idFactura, int idCliente) {
 this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.codigoProducto = codigoProducto;
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + idPedido + ", Fecha: " + fechaPedido + ", Total: " + total + ", Producto: " + codigoProducto;
    }
}