import java.util.HashMap;
import java.util.Map;

 class Pedido {
    private Map<Producto, Integer> productos; 
    private String id;

    public Pedido() {
    	productos = new HashMap<>();
    	this.id = generateId();
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
    public String getId() {
    	return id;
    }
    private String generateId() {
    	return "PEDIDO-" + System.currentTimeMillis();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID de Pedido: ").append(id).append("\n");
        sb.append("Detalles:\n");
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            sb.append("- ").append(entry.getKey().getNombre())
              .append(" | Cantidad: ").append(entry.getValue())
              .append("\n");
        }
        sb.append("Total: $").append(calcularTotal()).append("\n");
        return sb.toString();
    }
}
