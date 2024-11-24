package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteFrame extends JInternalFrame {
    private JTextField nombreField;
    private JButton agregarButton;

    public ClienteFrame() {
        setTitle("Gestión de Clientes");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(100, 20, 165, 25);
        add(nombreField);

        agregarButton = new JButton("Agregar Cliente");
        agregarButton.setBounds(10, 80, 150, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                JOptionPane.showMessageDialog(null, "Cliente agregado: " + nombre);
            }
        });
    }
}