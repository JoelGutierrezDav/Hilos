package GUI;

import javax.swing.*;

import BLL.Inventario;

import java.awt.*;
import DLL.ControllerCliente;
import DLL.ControllerPedido;
import DLL.ControllerProducto;
import DLL.ControllerFactura;
import BLL.Inventario;

public class MenuFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private ControllerCliente controllerCliente;
    private ControllerPedido controllerPedido;
    private ControllerProducto controllerProducto;
    private ControllerFactura controllerFactura;
    private Inventario inventario;

    public MenuFrame(ControllerCliente controllerCliente, ControllerPedido controllerPedido, ControllerProducto controllerProducto, ControllerFactura controllerFactura) {
        this.controllerCliente = controllerCliente;
        this.controllerPedido = controllerPedido;
        this.controllerProducto = controllerProducto;
        this.controllerFactura = controllerFactura;
        this.inventario = new Inventario();
        initialize();
    }

    private void initialize() {
        setTitle("Hilos Joel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        contentPane = new JPanel(); 
        contentPane.setLayout(new BorderLayout()); 
        setContentPane(contentPane);
        
        textArea = new JTextArea(); 
        textArea.setEditable(false); 
        contentPane.add(new JScrollPane(textArea), BorderLayout.CENTER); 
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menú de Clientes
        JMenu mnClientes = new JMenu("Clientes");
        mnClientes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnClientes);

        JMenuItem mntmCrearCliente = new JMenuItem("Agregar");
        mntmCrearCliente.addActionListener(e -> cambiarContenido(new VistaAgregarCliente(controllerCliente)));
        mnClientes.add(mntmCrearCliente);

        JMenuItem mntmMostrarClientes = new JMenuItem("Lista");
        mntmMostrarClientes.addActionListener(e -> cambiarContenido(new VistaListaClientes(controllerCliente)));
        mnClientes.add(mntmMostrarClientes);

        JMenuItem mntmBuscarCliente = new JMenuItem("Buscar");
        mntmBuscarCliente .addActionListener(e -> cambiarContenido(new VistaBuscarCliente(controllerCliente)));
        mnClientes.add(mntmBuscarCliente);

        JMenuItem mntmActualizarCliente = new JMenuItem("Actualizar");
        mntmActualizarCliente.addActionListener(e -> cambiarContenido(new VistaActualizarCliente(controllerCliente)));
        mnClientes.add(mntmActualizarCliente);

        JMenuItem mntmEliminarCliente = new JMenuItem("Eliminar");
        mntmEliminarCliente.addActionListener(e -> cambiarContenido(new VistaEliminarClientes(controllerCliente)));
        mnClientes.add(mntmEliminarCliente);

        // Menú de Productos
        JMenu mnProductos = new JMenu("Productos");
        mnProductos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnProductos);

        JMenuItem mntmAgregarProducto = new JMenuItem("Agregar");
        mntmAgregarProducto.addActionListener(e -> cambiarContenido(new VistaAgregarProducto(controllerProducto)));
        mnProductos.add(mntmAgregarProducto);

        JMenuItem mntmListarProductos = new JMenuItem("Listar");
        mntmListarProductos.addActionListener(e -> cambiarContenido(new VistaListaProducto(controllerProducto)));
        mnProductos.add(mntmListarProductos);

        JMenuItem mntmBuscarProducto = new JMenuItem("Buscar");
        mntmBuscarProducto.addActionListener(e -> cambiarContenido(new VistaBuscarProducto(controllerProducto)));
        mnProductos.add(mntmBuscarProducto);
        
     
        JMenuItem mntmAumentarStock = new JMenuItem("Aumentar Stock");
        mntmAumentarStock.addActionListener(e -> cambiarContenido(new VistaAumentarStock(controllerProducto)));
        mnProductos.add(mntmAumentarStock);

 
        JMenuItem mntmVerificarStock = new JMenuItem("Verificar Stock");
        mntmVerificarStock.addActionListener(e -> cambiarContenido(new VistaVerificarStock(inventario))); 
        mnProductos.add(mntmVerificarStock);
        
        // Menú de Pedidos
        JMenu mnPedidos = new JMenu("Pedidos");
        mnPedidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnPedidos);

        JMenuItem mntmAgregarPedido = new JMenuItem("Agregar");
        mntmAgregarPedido.addActionListener(e -> cambiarContenido(new VistaAgregarPedido(controllerPedido)));
        mnPedidos.add(mntmAgregarPedido);

        JMenuItem mntmListarPedidos = new JMenuItem("Listar");
        mntmListarPedidos.addActionListener(e -> cambiarContenido(new VistaListaPedidos(controllerPedido)));
        mnPedidos.add(mntmListarPedidos);

        // Menú de Facturas
        JMenu mnFacturas = new JMenu("Facturas");
        mnFacturas.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnFacturas);

        JMenuItem mntmCrearFactura = new JMenuItem("Crear");
		mntmCrearFactura.addActionListener(e -> cambiarContenido(new VistaAgregarFactura(inventario)));
        mnFacturas.add(mntmCrearFactura);

        JMenuItem mntmListarFacturas = new JMenuItem("Listar");
        mntmListarFacturas.addActionListener(e -> cambiarContenido(new VistaListaFacturas(controllerFactura)));
        mnFacturas.add(mntmListarFacturas);

        // Panel de contenido
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JPanel(), BorderLayout.CENTER); 
        
        
    }

    private void cambiarContenido(JPanel nuevoPanel) {
        contentPane.removeAll(); 
        contentPane.add(nuevoPanel, BorderLayout.CENTER); 
        contentPane.revalidate(); 
        contentPane.repaint(); 
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame frame = new MenuFrame(new ControllerCliente(), new ControllerPedido(), new ControllerProducto(), new ControllerFactura());
            frame.setVisible(true);
        });
    }
}