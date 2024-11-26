package GUI;

import javax.swing.*;
import DLL.ControllerPedido;
import BLL.Pedido;

public class VistaActualizarPedidos extends JPanel {
    private JTextField txtIdPedido, txtFecha, txtTotal;
    private JButton btnActualizar;
    private ControllerPedido controllerPedido;

    public VistaActualizarPedidos(ControllerPedido controllerPedido) {
        this.controllerPedido = controllerPedido;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(10, 20, 80, 25);
        add(lblIdPedido);

        txtIdPedido = new JTextField(20);
        txtIdPedido.setBounds(100, 20, 165, 25);
        add(txtIdPedido);


        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 60, 80, 25);
        add(lblFecha);

        txtFecha = new JTextField(20);
        txtFecha.setBounds(100, 60, 165, 25);
        add(txtFecha);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 100, 80, 25);
        add(lblTotal);

        txtTotal = new JTextField(20);
        txtTotal.setBounds(100, 100, 165, 25);
        add(txtTotal);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(10, 140, 255, 25);
        btnActualizar.addActionListener(e -> actualizarPedido());
        add(btnActualizar);
    }

    private void actualizarPedido() {
        String idStr = txtIdPedido.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String fecha = txtFecha.getText();
                double total = Double.parseDouble(txtTotal.getText());
                Pedido pedidoActualizado = new Pedido(id, fecha, total, "", 0, 0); 
                if (controllerPedido.modificarPedido(id, pedidoActualizado)) {
                    JOptionPane.showMessageDialog(this, "Pedido actualizado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID o total inválido.");
            }
        }
    }
}