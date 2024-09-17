import java.util.ArrayList;
import java.util.List;
public class PedidoProveedor {
	private List<Producto> productosaReponer;
		
	public PedidoProveedor() {
		productosaReponer = new ArrayList<>();
	}
	public void agregarProducto(Producto producto) {
		productosaReponer.add(producto);
	}
	public List<Producto> getProductosaReponer(){
		return productosaReponer;
	}
	public String generarListaPedido() {
		StringBuilder listaPedido = new StringBuilder();
		for (Producto producto : productosaReponer) {
			listaPedido.append("-").append(producto.getStock()).append("\n");
		}
		return listaPedido.toString();	}
		/*public double calcularTotal() {
			double total = 0;
			for (Producto producto: productos) { 
				total += producto.getPrecio();
			}
			return total;
		}*/
		
	}


