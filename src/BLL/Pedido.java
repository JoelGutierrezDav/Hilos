package BLL;
public class Pedido {
    private int idPedido;
    private String fechaPedido;
    private double total;
    private String codigoProducto;
    private int idFactura;
    private int idCliente;


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

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + idPedido + ", Fecha: " + fechaPedido + ", Total: " + total + ", Producto: " + codigoProducto;
    }
}
