package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacturaFrame extends JInternalFrame {
    private JTextField idField, fechaField, totalField, clienteIdField;
    private JButton agregarButton;

    public FacturaFrame(){
        setTitle("Gestión de Facturas");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 165, 25);
        add(idField);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setBounds(10, 60, 80, 25);
        add(fechaLabel);

        fechaField = new JTextField();
        fechaField.setBounds(100, 60, 165, 25);
        add(fechaField);

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setBounds(10, 100, 80, 25);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(100, 100, 165, 25);
        add(totalField);

        JLabel clienteIdLabel = new JLabel("Cliente ID:");
        clienteIdLabel.setBounds(10, 140, 80, 25);
        add(clienteIdLabel);

        clienteIdField = new JTextField();
        clienteIdField.setBounds(100, 140, 165, 25);
        add(clienteIdField);

        agregarButton = new JButton("Agregar Factura");
        agregarButton.setBounds(10, 180, 150, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String fecha = fechaField.getText();
                double total = Double.parseDouble(totalField.getText());
                int clienteId = Integer.parseInt(clienteIdField.getText());
                JOptionPane.showMessageDialog(null, "Factura agregada: ID " + id);
            }
        });
    }
}