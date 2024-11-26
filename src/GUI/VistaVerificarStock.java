package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import BLL.Producto;
import BLL.Inventario;

public class VistaVerificarStock extends JPanel {
    private JTextArea textArea;
    private Inventario inventario;

    public VistaVerificarStock(Inventario inventario) {
        this.inventario = inventario;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JButton btnRefresh = new JButton("Verificar Stock");
        btnRefresh.addActionListener(e -> mostrarProductosBajoStockMinimo());
        add(btnRefresh, BorderLayout.SOUTH);
        
        mostrarProductosBajoStockMinimo(); 
    }

    private void mostrarProductosBajoStockMinimo() {
        LinkedList<Producto> productosBajoStock = inventario.obtenerProductosBajoStockMinimo();
        if (productosBajoStock.isEmpty()) {
            textArea.setText("No hay productos con stock bajo o mínimo.");
        } else {
            StringBuilder sb = new StringBuilder("--- PRODUCTOS CON STOCK BAJO ---\n");
            for (Producto producto : productosBajoStock) {
                sb.append(producto).append("\n");
            }
            textArea.setText(sb.toString());
        }
    }
}