package GUI;

import javax.swing.*;
import DLL.ControllerCliente;

public class VistaEliminarClientes extends JPanel {
    private JTextField txtIdCliente;
    private JButton btnEliminar;
    private ControllerCliente controllerCliente;

    public VistaEliminarClientes(ControllerCliente controllerCliente) {
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

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(10, 60, 255, 25);
        btnEliminar.addActionListener(e -> eliminarCliente());
        add(btnEliminar);
    }

    private void eliminarCliente() {
        String idStr = txtIdCliente.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                if (controllerCliente.eliminarCliente(id)) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }
}
