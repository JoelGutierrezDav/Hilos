import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Inventario {
	private List<Producto> productos;
	public Inventario() {
		productos = new ArrayList<>();
	}
	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}
	public List<Producto> getProductos(){
		return productos;
	}
	public Producto buscarProductoPorCodigo(String codigo) {
		for (Producto producto : productos) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				return producto;
			}
		}
		return null;
	}
	public void verificarStock() {
		StringBuilder alerta = new StringBuilder();
		for (Producto producto : productos) {
			if(producto.esBajoEnStock()) {
				alerta.append(producto.getNombre()).append(" está bajo en stock (Cantidad: ").append(producto.getStock()).append(")\n");
			}
		}
		if (alerta.length() > 0) {
			JOptionPane.showMessageDialog(null, alerta.toString(), "Productos con bajo stock", JOptionPane.WARNING_MESSAGE);
		}
	}
}
