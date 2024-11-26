package GUI;

import javax.swing.*;
import DLL.ControllerFactura;

public class VistaEliminarFactura extends JPanel {
    private JTextField txtIdFactura;
    private JButton btnEliminar;
    private ControllerFactura controllerFactura;

    public VistaEliminarFactura(ControllerFactura controllerFactura) {
        this.controllerFactura = controllerFactura;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblIdFactura = new JLabel("ID Factura:");
        lblIdFactura.setBounds(10, 20, 80, 25);
        add(lblIdFactura);

        txtIdFactura = new JTextField(20);
        txtIdFactura.setBounds(100, 20, 165, 25);
        add(txtIdFactura);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(10, 60, 255, 25);
        btnEliminar.addActionListener(e -> eliminarFactura());
        add(btnEliminar);
    }

    private void eliminarFactura() {
        int id = Integer.parseInt(txtIdFactura.getText());
        if (controllerFactura.eliminarFactura(id)) {
            JOptionPane.showMessageDialog(this, "Factura eliminada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Factura no encontrada.");
        }
    }
}
