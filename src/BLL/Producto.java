package BLL;
public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;

    public Producto(int id, String codigo, String nombre, String descripcion, double precio, int stock, int stockMinimo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }


    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               " | Código: " + codigo + 
               " | Nombre: " + nombre + 
               " | Descripción: " + descripcion + 
               " | Precio: " + precio + 
               " | Stock: " + stock + 
               " | Stock Mínimo: " + stockMinimo;
    }
}
