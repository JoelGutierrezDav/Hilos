package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setTitle("Hilos Joel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 128, 128));
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmcrearCliente = new JMenuItem("Agregar");
		mnClientes.add(mntmcrearCliente);
		
		JMenuItem mntmmostrarClientes = new JMenuItem("Lista");
		mnClientes.add(mntmmostrarClientes);
		
		JMenuItem mntmbuscarCliente = new JMenuItem("Buscar");
		mnClientes.add(mntmbuscarCliente);
		
		JMenuItem mntmactualizarCliente = new JMenuItem("Actualizar");
		mnClientes.add(mntmactualizarCliente);
		
		JMenuItem mntmeliminarCliente = new JMenuItem("Eliminar");
		mnClientes.add(mntmeliminarCliente);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenuItem mntmagregarProducto = new JMenuItem("Agregar");
		mnProductos.add(mntmagregarProducto);
		
		JMenuItem mntmobtenerProductos = new JMenuItem("Lista");
		mnProductos.add(mntmobtenerProductos);
		
		JMenuItem mntmbuscarProductoPorCodigo = new JMenuItem("Buscar");
		mnProductos.add(mntmbuscarProductoPorCodigo);
		
		JMenuItem mntmmodificarProducto = new JMenuItem("Aumentar Stock");
		mnProductos.add(mntmmodificarProducto);
		
		JMenuItem mntmverificarStock = new JMenuItem("Verificar Stock");
		mnProductos.add(mntmverificarStock);
		
		JMenu mnPedidos = new JMenu("Pedidos");
		menuBar.add(mnPedidos);
		
		JMenuItem mntmagregarPedido = new JMenuItem("Agregar");
		mnPedidos.add(mntmagregarPedido);
		
		JMenuItem mntmmostrarPedidos = new JMenuItem("Lista");
		mnPedidos.add(mntmmostrarPedidos);
		
		JMenuItem mntmbuscarPedido = new JMenuItem("Buscar");
		mnPedidos.add(mntmbuscarPedido);
		
		JMenuItem mntmmodificarPedido = new JMenuItem("Actualizar");
		mnPedidos.add(mntmmodificarPedido);
		
		JMenuItem mntmeliminarPedido = new JMenuItem("Eliminar");
		mnPedidos.add(mntmeliminarPedido);
		
		JMenu mnFacturas = new JMenu("Facturas");
		menuBar.add(mnFacturas);
		
		JMenuItem mntmmostrarFacturas = new JMenuItem("Lista");
		mnFacturas.add(mntmmostrarFacturas);
		
		JMenuItem mntmcrearFactura = new JMenuItem("Crear");
		mnFacturas.add(mntmcrearFactura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
