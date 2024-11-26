package GUI;

import javax.swing.*;
import DLL.ControllerPedido;
import BLL.Pedido;

public class VistaAgregarPedido extends JPanel {
    private JTextField txtFecha, txtTotal, txtCodigoProducto, txtIdFactura, txtIdCliente;
    private JButton btnAgregar;
    private ControllerPedido controllerPedido;

    public VistaAgregarPedido(ControllerPedido controllerPedido) {
        this.controllerPedido = controllerPedido;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 20, 80, 25);
        add(lblFecha);

        txtFecha = new JTextField(20);
        txtFecha.setBounds(100, 20, 165, 25);
        add(txtFecha);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 60, 80, 25);
        add(lblTotal);

        txtTotal = new JTextField(20);
        txtTotal.setBounds(100, 60, 165, 25);
        add(txtTotal);

        JLabel lblCodigoProducto = new JLabel("Código Producto:");
        lblCodigoProducto.setBounds(10, 100, 120, 25);
        add(lblCodigoProducto);

        txtCodigoProducto = new JTextField(20);
        txtCodigoProducto.setBounds(130, 100, 135, 25);
        add(txtCodigoProducto);

        JLabel lblIdFactura = new JLabel("ID Factura:");
        lblIdFactura.setBounds(10, 140, 80, 25);
        add(lblIdFactura);

        txtIdFactura = new JTextField(20);
        txtIdFactura.setBounds(100, 140, 165, 25);
        add(txtIdFactura);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(10, 180, 80, 25);
        add(lblIdCliente);

        txtIdCliente = new JTextField(20);
        txtIdCliente.setBounds(100, 180, 165, 25);
        add(txtIdCliente);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 220, 255, 25);
        btnAgregar.addActionListener(e -> agregarPedido());
        add(btnAgregar);
    }

    private void agregarPedido() {
        String fecha = txtFecha.getText();
        double total = Double.parseDouble(txtTotal.getText());
        String codigoProducto = txtCodigoProducto.getText();
        int idFactura = Integer.parseInt(txtIdFactura.getText());
        int idCliente = Integer.parseInt(txtIdCliente.getText());

        Pedido nuevoPedido = new Pedido(fecha, total, codigoProducto, idFactura, idCliente);
        controllerPedido.agregarPedido(nuevoPedido);
        JOptionPane.showMessageDialog(this, "Pedido agregado exitosamente.");
    }
}