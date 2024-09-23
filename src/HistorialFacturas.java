import java.util.ArrayList;

import javax.swing.JOptionPane;

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
            sb.append(factura.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Facturas", JOptionPane.INFORMATION_MESSAGE);
    }
}
