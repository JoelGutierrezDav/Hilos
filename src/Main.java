import javax.swing.JOptionPane;

public class Main {
	private static final String codigoProducto = null;
	private static Inventario inventario = new Inventario();
	public static void main (String[] args) {
		while (true) {
			String[] opciones = {"Agregar productos", "Mostrar productos", "Crear pedido Proveedor", "Crear factura", "Verificar stock", "Salir"};
			int opcion = JOptionPane.showOptionDialog(null, "Elija una opcion: ", "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
			
			switch (opcion) {
			case 0:
				agregarProductoAlInventario();
				break;
			case 1:
				mostrarProductos();
				break;
			case 2:
				crearPedidoProveedor();
				break;
			case 3:
				crearFactura();
				break;
			case 4:
				inventario.verificarStock();
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Saliendo");
				return;
			default:
				JOptionPane.showMessageDialog(null, "Opción no valida");
			}
		}
	}
	
	private static void agregarProductoAlInventario() {
		String codigo = JOptionPane.showInputDialog("Ingresar codigo del Producto");
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
		String descripcion = JOptionPane.showInputDialog("Ingresar descripcion del producto");
		String precio$ = JOptionPane.showInputDialog("Ingrese el precio del producto");
		String cant = JOptionPane.showInputDialog("Ingresar cantidad del producto");
		try {
			double precio = Double.parseDouble(precio$);
			int stock = Integer.parseInt(cant);
			Producto producto = new Producto(codigo, nombre, descripcion, precio, stock);
			inventario.agregarProducto(producto);
			JOptionPane.showMessageDialog(null, "Producto agregado.");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Datos de precio o stock invalidos");
		}
	}
	private static void mostrarProductos() {
        StringBuilder listaProductos = new StringBuilder();
        for (Producto producto : inventario.getProductos()) {
            listaProductos.append(producto).append("\n");
        }
        if (listaProductos.length() == 0) {
            listaProductos.append("No hay productos en el inventario.");
        }
        JOptionPane.showMessageDialog(null, listaProductos.toString(), "Productos en Inventario", JOptionPane.INFORMATION_MESSAGE);
    }
	private static void crearPedidoProveedor() {
		PedidoProveedor pedidoProveedor = new PedidoProveedor();
		StringBuilder productosBajoStock = new StringBuilder("Productos con bajo stock: \n");
		for (Producto producto : inventario.getProductos()) {
			if (producto.getStock() < producto.getStockMinimo()) {
				productosBajoStock.append(producto.getNombre()).append(" - Stock actual: ").append(producto.getStock()).append("\n");
				pedidoProveedor.agregarProducto(producto);
			}
		}
		if (productosBajoStock.length() > 0) {
			JOptionPane.showMessageDialog(null, productosBajoStock.toString(), "Pedido al Proveedor", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "No hay productos con bajo stock.", "Pedido al Proveedor", JOptionPane.INFORMATION_MESSAGE);
		}
		while (true) {
			String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto para agregar al pedido ( o 'fin' para terminar):");
			if (nombreProducto.equalsIgnoreCase("fin")) {
				break;
			}
			Producto producto = inventario.buscarProductoPorCodigo(nombreProducto);
			if (producto != null) {
				pedidoProveedor.agregarProducto(producto);
				JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
			}else {
				JOptionPane.showMessageDialog(null, "Producto no encontrado");
			}
		}
    }

	private static void crearFactura() {
		Pedido pedido = new Pedido();
		mostrarProductos();
		
		String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
		Cliente cliente = new Cliente(nombreCliente);
		
		while (true) {
			String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto");
			if (nombreProducto.equalsIgnoreCase("fin")) {
				break;
			}
			Producto producto = inventario.buscarProductoPorCodigo(codigoProducto);
			if (producto != null) {
				pedido.agregarProducto(producto);
				JOptionPane.showMessageDialog(null, "Producto agregado.");
			}else {
				JOptionPane.showMessageDialog(null, "Producto no encontrado.");
			}
		}
		Factura factura = new Factura(cliente, pedido);
		factura.imprimirFactura();
	}
	
}
