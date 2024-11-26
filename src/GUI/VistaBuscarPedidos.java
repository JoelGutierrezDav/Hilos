package GUI;

import javax.swing.*;
import DLL.ControllerPedido;
import BLL.Pedido;

public class VistaBuscarPedidos extends JPanel {
    private JTextField txtIdPedido;
    private JButton btnBuscar;
    private ControllerPedido controllerPedido;

    public VistaBuscarPedidos(ControllerPedido controllerPedido) {
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

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(10, 60, 255, 25);
        btnBuscar.addActionListener(e -> buscarPedido());
        add(btnBuscar);
    }

    private void buscarPedido() {
        String idStr = txtIdPedido.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt (idStr);
                Pedido pedido = controllerPedido.buscarPedido(id);
                if (pedido != null) {
                    JOptionPane.showMessageDialog(this, "Pedido encontrado: " + pedido);
                } else {
                    JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }
}