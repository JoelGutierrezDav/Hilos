package GUI;

import javax.swing.*;
import java.awt.*;
import DLL.ControllerFactura;
import BLL.Factura;
import java.util.LinkedList;

public class VistaListaFacturas extends JPanel {
    private ControllerFactura controllerFactura;
    private JTextArea txtAreaFacturas;

    public VistaListaFacturas(ControllerFactura controllerFactura) {
         this.controllerFactura = controllerFactura;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        JButton btnListar = new JButton("Listar Facturas");
        btnListar.addActionListener(e -> listarFacturas());
        add(btnListar, BorderLayout.NORTH);

        txtAreaFacturas = new JTextArea(10, 20);
        txtAreaFacturas.setEditable(false);
        add(new JScrollPane(txtAreaFacturas), BorderLayout.CENTER);
    }

    private void listarFacturas() {
        LinkedList<Factura> facturas = controllerFactura.mostrarFacturas();
        
        if (facturas.isEmpty ()) {
            txtAreaFacturas.setText("No hay facturas registradas.");
        } else {
            StringBuilder sb = new StringBuilder("--- LISTA DE FACTURAS ---\n");
            for (Factura factura : facturas) {
                sb.append(factura).append("\n");
            }
            txtAreaFacturas.setText(sb.toString());
        }
    }
}