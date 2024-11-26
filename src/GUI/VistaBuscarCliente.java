package GUI;

import javax.swing.*;
import DLL.ControllerCliente;
import BLL.Cliente;

public class VistaBuscarCliente extends JPanel {
    private JTextField txtIdCliente;
    private JButton btnBuscar;
    private ControllerCliente controllerCliente;

    public VistaBuscarCliente(ControllerCliente controllerCliente) {
        this.controllerCliente = controllerCliente;
        initialize();
    }

    private void initialize() {
        setLayout(null);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(10, 20, 80, 25);
        add(lblIdCliente);

        txtIdCliente = new JTextField(20);
        txtIdCliente.setBounds(100, 20, 165, 25);
        add(txtIdCliente);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(10, 60, 255, 25);
        btnBuscar.addActionListener(e -> buscarCliente());
        add(btnBuscar);
    }

    private void buscarCliente() {
        String idStr = txtIdCliente.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Cliente cliente = controllerCliente.buscarCliente(id);
 if (cliente != null) {
                    JOptionPane.showMessageDialog(this, "Cliente encontrado: " + cliente);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }
}