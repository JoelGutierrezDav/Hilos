package GUI;

import javax.swing.*;
import DLL.ControllerCliente;
import BLL.Cliente;

public class VistaAgregarCliente extends JPanel {
    private JTextField txtNombreDelCliente;
    private JButton btnAgregar;
    private ControllerCliente controllerCliente;

    public VistaAgregarCliente(ControllerCliente controllerCliente) {
        this.controllerCliente = controllerCliente;
        initialize();
    }

    private void initialize() {
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 20, 80, 25);
        add(lblNombre);

        txtNombreDelCliente = new JTextField(20);
        txtNombreDelCliente.setBounds(100, 20, 165, 25);
        add(txtNombreDelCliente);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 80, 255, 25);
        btnAgregar.addActionListener(e -> agregarCliente());
        add(btnAgregar);
    }

    private void agregarCliente() {
        String nombre = txtNombreDelCliente.getText();
        if (nombre != null && !nombre.trim().isEmpty()) {
            Cliente nuevoCliente = new Cliente(nombre);
            controllerCliente.crearCliente(nuevoCliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
        }
    }
}