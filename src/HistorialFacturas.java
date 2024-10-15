import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HistorialFacturas {
    private ArrayList<Factura> facturas;

    public HistorialFacturas() {
        this.facturas = new ArrayList<>();
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
    }

    public void mostrarFacturas() {
        if (facturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay facturas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Factura factura : facturas) {
            sb.append(factura.toString())
              .append(" | Total: $").append(factura.calcularTotal())
              .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Facturas", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarProductosMasVendidos() {
        Map<Producto, Integer> productosVendidos = new HashMap<>();

        for (Factura factura : facturas) {
            for (Map.Entry<Producto, Integer> entry : factura.getPedido().getProductos().entrySet()) {
                Producto producto = entry.getKey();
                int cantidad = entry.getValue();
                productosVendidos.put(producto, productosVendidos.getOrDefault(producto, 0) + cantidad);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (productosVendidos.isEmpty()) {
            sb.append("No se han vendido productos.");
        } else {
            sb.append("Productos más vendidos:\n");

            
            List<Map.Entry<Producto, Integer>> listaOrdenada = new ArrayList<>(productosVendidos.entrySet());
            listaOrdenada.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

           
            for (Map.Entry<Producto, Integer> entry : listaOrdenada) {
                sb.append("- ").append(entry.getKey().getNombre())
                  .append(" | Cantidad vendida: ").append(entry.getValue())
                  .append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Productos Más Vendidos", JOptionPane.INFORMATION_MESSAGE);
    }

}
