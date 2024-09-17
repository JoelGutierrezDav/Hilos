
public class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private static final int STOCK_MINIMO = 5;
	
	public Producto(String codigo, String nombre, String descipcion, double precio, int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descipcion;
		this.precio = precio;
		this.stock = stock;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void reducirStock(int cantidad) {
		this.stock -= cantidad;
	}
	public boolean esBajoEnStock() {
		return stock < STOCK_MINIMO;
	}
	@Override
    public String toString() {
		return "Código: " + codigo + " | " + nombre + " - $" + precio + " | Stock: " + stock + " | Descripción: " + descripcion;
    }
}
