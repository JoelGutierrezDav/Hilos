package GUI;

import javax.swing.*;
import DLL.ControllerProducto;
import BLL.Producto;

public class VistaAgregarProducto extends JPanel {
    private JTextField txtCodigo, txtNombre, txtDescripcion, txtPrecio, txtStock, txtStockMinimo;
    private JButton btnAgregar;
    private ControllerProducto controllerProducto;

    public VistaAgregarProducto(ControllerProducto controllerProducto) {
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

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField(20);
        txtNombre.setBounds(100, 60, 165, 25);
        add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 100, 80, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextField(20);
        txtDescripcion.setBounds(100, 100, 165, 25);
        add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 140, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField(20);
        txtPrecio.setBounds(100, 140, 165, 25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(10, 180, 80, 25);
        add(lblStock);

        txtStock = new JTextField(20);
        txtStock.setBounds(100, 180, 165, 25);
        add(txtStock);

        JLabel lblStockMinimo = new JLabel("Stock Mínimo:");
        lblStockMinimo.setBounds(10, 220, 100, 25);
        add(lblStockMinimo);

        txtStockMinimo = new JTextField(20);
        txtStockMinimo.setBounds(120, 220, 165, 25);
        add(txtStockMinimo);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 260, 255, 25);
        btnAgregar.addActionListener(e -> agregarProducto());
        add(btnAgregar);
    }

    private void agregarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());
        int stockMinimo = Integer.parseInt(txtStockMinimo.getText());

        Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, stock, stockMinimo);
        controllerProducto.agregarProducto(nuevoProducto);
        JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
    }
}