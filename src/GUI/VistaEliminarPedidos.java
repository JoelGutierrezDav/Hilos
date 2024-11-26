package GUI;

import javax.swing.*;
import DLL.ControllerPedido;

public class VistaEliminarPedidos extends JPanel {
    private JTextField txtIdPedido;
    private JButton btnEliminar;
    private ControllerPedido controllerPedido;

    public VistaEliminarPedidos(ControllerPedido controllerPedido) {
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

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(10, 60, 255, 25);
        btnEliminar.addActionListener(e -> eliminarPedido());
        add(btnEliminar);
    }

    private void eliminarPedido() {
        String idStr = txtIdPedido.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                if (controllerPedido.eliminarPedido(id)) {
                    JOptionPane.showMessageDialog(this, "Pedido eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }
}