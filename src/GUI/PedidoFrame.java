package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoFrame extends JInternalFrame {
    private JTextField fechaField, totalField, codigoProductoField, idFacturaField, idClienteField;
    private JButton agregarButton;

    public PedidoFrame() {
        setTitle("Gestión de Pedidos");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setBounds(10, 20, 80, 25);
        add(fechaLabel);

        fechaField = new JTextField();
        fechaField.setBounds(100, 20, 165, 25);
        add(fechaField);

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setBounds(10, 60, 80, 25);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(100, 60, 165, 25);
        add(totalField);

        JLabel codigoProductoLabel = new JLabel("Código Producto:");
        codigoProductoLabel.setBounds(10, 100, 120, 25);
        add(codigoProductoLabel);

        codigoProductoField = new JTextField();
        codigoProductoField.setBounds(130, 100, 135, 25);
        add(codigoProductoField);

        JLabel idFacturaLabel = new JLabel("ID Factura:");
        idFacturaLabel.setBounds(10, 140, 80, 25);
        add(idFacturaLabel);

        idFacturaField = new JTextField();
        idFacturaField.setBounds(100, 140, 165, 25);
        add(idFacturaField);

        JLabel idClienteLabel = new JLabel("ID Cliente:");
        idClienteLabel.setBounds(10, 180, 80, 25);
        add(idClienteLabel);

        idClienteField = new JTextField();
        idClienteField.setBounds(100, 180, 165, 25);
        add(idClienteField);

        agregarButton = new JButton("Agregar Pedido");
        agregarButton.setBounds(10, 220, 150, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = fechaField.getText();
                double total = Double.parseDouble(totalField.getText());
                String codigoProducto = codigoProductoField.getText();
                int idFactura = Integer.parseInt(idFacturaField.getText());
                int idCliente = Integer.parseInt(idClienteField.getText());
                JOptionPane.showMessageDialog(null, "Pedido agregado: " + codigoProducto);
            }
        });
    }
}
