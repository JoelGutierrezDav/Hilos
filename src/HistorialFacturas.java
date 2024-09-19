import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class HistorialFacturas {
  
    private List<Factura> facturas = new ArrayList<>();

    
    public void mostrarFacturas() {
        if (facturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay facturas para mostrar.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Factura factura : facturas) {
                sb.append(factura.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Facturas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    public void agregarFactura(Factura factura) {
        facturas.add(factura);
    }
}
