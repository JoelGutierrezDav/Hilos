package GUI;

import javax.swing.*;

import BLL.Producto;
import DLL.ControllerProducto;

public class VistaAumentarStock extends JPanel {
    private JTextField txtCodigoProducto, txtCantidad;
    private JButton btnAumentar;
    private ControllerProducto controllerProducto;

    public VistaAumentarStock(ControllerProducto controllerProducto) {
        this.controllerProducto = controllerProducto;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblCodigo = new JLabel("Código Producto:");
        lblCodigo.setBounds(10, 20, 120, 25);
        add(lblCodigo);

        txtCodigoProducto = new JTextField(20);
        txtCodigoProducto.setBounds(130, 20, 165, 25);
        add(txtCodigoProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10, 60, 120, 25);
        add(lblCantidad);

        txtCantidad = new JTextField(20);
        txtCantidad.setBounds(130, 60, 165, 25);
        add(txtCantidad);

        btnAumentar = new JButton("Aumentar Stock");
        btnAumentar.setBounds(10, 100, 255, 25);
        btnAumentar.addActionListener(e -> aumentarStock());
        add(btnAumentar);
    }

    private void aumentarStock() {
        String codigo = txtCodigoProducto.getText();
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            Producto producto = controllerProducto.buscarProductoPorCodigo(codigo);
            if (producto != null) {
                producto.setStock(producto.getStock() + cantidad);
                controllerProducto.modificarProducto(producto);
                JOptionPane.showMessageDialog(this, "Stock aumentado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.");
        }
    }
}