package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoFrame extends JFrame {
    private JTextField codigoField, nombreField, descripcionField, precioField, stockField, stockMinimoField;
    private JButton agregarButton;

    public ProductoFrame() {
        setTitle("Gestión de Productos");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(10, 100, 80, 25);
        add(descripcionLabel);

        descripcionField = new JTextField();
        descripcionField.setBounds(100, 100, 165, 25);
        add(descripcionField);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(10, 140, 80, 25);
        add(precioLabel);

        precioField = new JTextField();
        precioField.setBounds(100, 140, 165, 25);
        add(precioField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(10, 180, 80, 25);
        add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(100, 180, 165, 25);
        add(stockField);

        JLabel stockMinimoLabel = new JLabel("Stock Mínimo:");
        stockMinimoLabel.setBounds(10, 220, 100, 25);
        add(stockMinimoLabel);

        stockMinimoField = new JTextField();
        stockMinimoField.setBounds(120, 220, 145, 25);
        add(stockMinimoField);

        agregarButton = new JButton("Agregar Producto");
        agregarButton.setBounds(10, 260, 150, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigo = codigoField.getText();
                String nombre = nombreField.getText();
                String descripcion = descripcionField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int stock = Integer.parseInt(stockField.getText());
                int stockMinimo = Integer.parseInt(stockMinimoField.getText());

                JOptionPane.showMessageDialog(null, "Producto agregado: " + nombre);
            }
        });
    }

    public static void main(String[] args) {
        ProductoFrame frame = new ProductoFrame();
        frame.setVisible(true);
    }
}
