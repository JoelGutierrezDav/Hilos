package GUI;

import javax.swing.*;
import DLL.ControllerCliente;
import BLL.Cliente;

public class VistaActualizarCliente extends JPanel {
    private JTextField txtId, txtNombre;
    private JButton btnActualizar;
    private ControllerCliente controllerCliente;

    public VistaActualizarCliente(ControllerCliente controllerCliente) {
        this.controllerCliente = controllerCliente;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblId = new JLabel("ID Cliente:");
        lblId.setBounds(10, 20, 80, 25);
        add(lblId);

        txtId = new JTextField(20);
        txtId.setBounds(100, 20, 165, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField(20);
        txtNombre.setBounds(100, 60, 165, 25);
        add(txtNombre);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(10, 100, 255, 25);
        btnActualizar.addActionListener(e -> actualizarCliente());
        add(btnActualizar);
    }

    private void actualizarCliente() {
        String idStr = txtId.getText();
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Cliente cliente = controllerCliente.buscarCliente(id);
                if (cliente != null) {
                    cliente.setNombre(txtNombre.getText());
                    controllerCliente.actualizarCliente(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }
}
