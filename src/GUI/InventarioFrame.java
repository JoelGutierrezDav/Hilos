package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioFrame extends JInternalFrame {
    private JTextField codigoField, nombreField, precioField, stockField;
    private JButton agregarButton;

    public InventarioFrame() {
        setTitle("Gestión de Inventario");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel codigoLabel = new JLabel("Código:");
        codigoLabel.setBounds(10, 20, 80, 25);
        add(codigoLabel);

        codigoField = new JTextField();
        codigoField.setBounds(100, 20, 165, 25);
        add(codigoField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 60, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(100, 60, 165, 25);
        add(nombreField);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(10, 100, 80, 25);
        add(precioLabel);

        precioField = new JTextField();
        precioField.setBounds(100, 100, 165, 25);
        add(precioField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(10, 140, 80, 25);
        add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(100, 140, 165, 25);
        add(stockField);

        agregarButton = new JButton("Agregar Producto");
        agregarButton.setBounds(10, 180, 150, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();
                String nombre = nombreField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int stock = Integer.parseInt(stockField.getText());
                JOptionPane.showMessageDialog(null, "Producto agregado: " + nombre);
            }
        });
    }
}
