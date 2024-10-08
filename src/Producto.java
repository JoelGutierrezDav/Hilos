
class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private int stockMinimo;
	private static final int STOCK_MINIMO = 5;
	
    public Producto(String codigo, String nombre, String descripcion, double precio, int stock, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
      
	}
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public void reducirStock(int cantidad) {
		this.stock -= cantidad;
	}
	public void aumentarStock(int cantidad) {
		this.stock += cantidad;
	}
	public boolean esBajoEnStock() {
		return stock < STOCK_MINIMO;
	}
	
	@Override
    public String toString() {
		return "Código: " + codigo + " | " + nombre + " - $" + precio + " | Stock: " + stock + " | Descripción: " + descripcion;
    }
}
