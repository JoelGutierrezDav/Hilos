package GUI;

import javax.swing.*;
import DLL.ControllerProducto;
import BLL.Producto;

public class VistaBuscarProducto extends JPanel {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private ControllerProducto controllerProducto;

    public VistaBuscarProducto(ControllerProducto controllerProducto) {
        this.controllerProducto = controllerProducto;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(10, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField(20);
        txtCodigo.setBounds(100, 20, 165, 25);
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(10 , 60, 255, 25);
        btnBuscar.addActionListener(e -> buscarProducto());
        add(btnBuscar);
    }

    private void buscarProducto() {
        String codigo = txtCodigo.getText();
        if (codigo != null) {
            Producto producto = controllerProducto.buscarProductoPorCodigo(codigo);
            if (producto != null) {
                JOptionPane.showMessageDialog(this, "Producto encontrado: " + producto);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        }
    }
}