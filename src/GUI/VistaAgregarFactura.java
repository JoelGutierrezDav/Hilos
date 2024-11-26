package GUI;

import javax.swing.*;
import BLL.Cliente;
import BLL.Producto;
import DLL.ControllerFactura;
import BLL.Inventario;

import java.util.LinkedList;

public class VistaAgregarFactura extends JPanel {
    private JTextField txtIdCliente;
    private JButton btnAgregar;
    private ControllerFactura controllerFactura;
    private Inventario inventario;

    private LinkedList<Producto> productosSeleccionados;
    private LinkedList<Integer> cantidadesSeleccionadas;

    public VistaAgregarFactura(Inventario inventario) {
        this.inventario = inventario;
        controllerFactura = new ControllerFactura();
        productosSeleccionados = new LinkedList<>();
        cantidadesSeleccionadas = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        setLayout(null); 

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(10, 20, 80, 25);
        add(lblIdCliente);

        txtIdCliente = new JTextField(20);
        txtIdCliente.setBounds(100, 20, 165, 25);
        add(txtIdCliente);

        JButton btnSeleccionarProductos = new JButton("Seleccionar Productos");
        btnSeleccionarProductos.setBounds(10, 60, 255, 25);
        btnSeleccionarProductos.addActionListener(e -> seleccionarProductos());
        add(btnSeleccionarProductos);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 100, 255, 25);
        btnAgregar.addActionListener(e -> agregarFactura());
        add(btnAgregar);
    }

    private void seleccionarProductos() {
        JFrame frameSeleccionarProductos = new JFrame("Seleccionar Productos");
        frameSeleccionarProductos.setSize(400, 300);
        frameSeleccionarProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        DefaultListModel<Producto> modeloLista = new DefaultListModel<>();
        JList<Producto> listaProductos = new JList<>(modeloLista);
		LinkedList<Producto> productos = inventario.getProductos();
        for (Producto producto : productos) {
            modeloLista.addElement(producto);
        }

        JTextField txtCantidad = new JTextField(10);
        JButton btnAgregarProducto = new JButton("Agregar Producto");

        btnAgregarProducto.addActionListener(e -> {
            Producto productoSeleccionado = listaProductos.getSelectedValue();
            if (productoSeleccionado != null) {
                try {
                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    productosSeleccionados.add(productoSeleccionado);
                    cantidadesSeleccionadas.add(cantidad);
                    JOptionPane.showMessageDialog(frameSeleccionarProductos, "Producto agregado a la factura.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frameSeleccionarProductos, "Cantidad inválida.");
                }
            } else {
                JOptionPane.showMessageDialog(frameSeleccionarProductos, "Seleccione un producto.");
            }
        });

        panel.add(new JScrollPane(listaProductos));
        panel.add(new JLabel("Cantidad:"));
        panel.add(txtCantidad);
        panel.add(btnAgregarProducto);

        frameSeleccionarProductos.add(panel);
        frameSeleccionarProductos.setVisible(true);
    }

    private void agregarFactura() {
        int idCliente;
        try {
            idCliente = Integer.parseInt(txtIdCliente.getText());
            Cliente cliente = new Cliente(idCliente, "Nombre del Cliente");

            controllerFactura.crearFactura(cliente, productosSeleccionados, cantidadesSeleccionadas);
            JOptionPane.showMessageDialog(this, "Factura creada exitosamente.");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de cliente inválido.");
        }
    }
}