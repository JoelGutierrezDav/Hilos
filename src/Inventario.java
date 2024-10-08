import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public void aumentarStockProducto(String codigo, int cantidad) {
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto != null) {
            producto.aumentarStock(cantidad);
            JOptionPane.showMessageDialog(null, "Stock actualizado " + producto.getNombre() + ": " + producto.getStock());
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    public void reducirStockProducto(String codigo, int cantidad) {
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto != null) {
            producto.reducirStock(cantidad);
            JOptionPane.showMessageDialog(null, "Stock actualizado " + producto.getNombre() + ": " + producto.getStock());
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    public void verificarStock() {
        StringBuilder alerta = new StringBuilder();
        for (Producto producto : productos) {
            if (producto.esBajoEnStock()) {
                alerta.append(producto.getNombre()).append(" está bajo en stock (Cantidad: ").append(producto.getStock()).append(")\n");
            }
        }
        if (alerta.length() > 0) {
            JOptionPane.showMessageDialog(null, alerta.toString(), "Productos con bajo stock", JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(this.productos);
    }
    
}