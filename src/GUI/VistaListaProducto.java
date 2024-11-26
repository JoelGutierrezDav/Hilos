package GUI;

import javax.swing.*;
import DLL.ControllerProducto;
import BLL.Producto;
import java.util.LinkedList;

public class VistaListaProducto extends JPanel {
    private ControllerProducto controllerProducto;

    public VistaListaProducto(ControllerProducto controllerProducto) {
        this.controllerProducto = controllerProducto;
        initialize();
    }

    private void initialize() {
        setLayout(null); 

        JButton btnListar = new JButton("Listar Productos");
        btnListar.setBounds(10, 20, 255, 25);
        btnListar.addActionListener(e -> listarProductos());
        add(btnListar);
    }

    private void listarProductos() {
        LinkedList<Producto> productos = controllerProducto.obtenerProductos();
        StringBuilder sb = new StringBuilder("--- LISTA DE PRODUCTOS ---\n");
        for (Producto producto : productos) {
            sb.append(producto).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    
    public static void mostrarVista() {
        JFrame frame = new JFrame("Lista de Productos");
        ControllerProducto controllerProducto = new ControllerProducto();
        VistaListaProducto vista = new VistaListaProducto(controllerProducto);
        frame.setContentPane(vista);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}