import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private Map<Producto, Integer> productos; 

    public Pedido() {
        productos = new HashMap<>();
    }

    
    public void agregarProducto(Producto producto, int cantidad) {
        if (productos.containsKey(producto)) {
            productos.put(producto, productos.get(producto) + cantidad);
        } else {
            productos.put(producto, cantidad);
        }
    }

    
    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    
    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
}
