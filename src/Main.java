import javax.swing.JOptionPane;

public class Main {
    private static final String codigoProducto = null;
    private static Inventario inventario = new Inventario();
    private static HistorialFacturas historialFacturas = new HistorialFacturas();
    private static HistorialPedidos historialPedidos = new HistorialPedidos();
    
    public static void main(String[] args) {
    	cargarProductosPredefinidos();
        while (true) {
            String[] opciones = {"Agregar productos", "Mostrar productos", "Crear pedido Proveedor", "Crear factura", "Verificar stock", "Mostrar Facturas", "Mostrar Pedidos", "Salir"};
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
                    historialFacturas.mostrarFacturas(); 
                    break;
                case 6:
                    historialPedidos.mostrarPedidos();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no valida");
            }
        }
    }
    
    private static void cargarProductosPredefinidos() {
        inventario.agregarProducto(new Producto("P127", "Patita de Recta", "Con Colita", 3500, 20, 5));
        inventario.agregarProducto(new Producto("P351", "Patita de Recta", "Sin Colita", 3500, 5, 5));
        inventario.agregarProducto(new Producto("CR1/32", "Patita Despuntadora", "Derecho", 4500, 4, 5));
        inventario.agregarProducto(new Producto("CR1/16N", "Patita Despuntadora", "Derecho", 4500, 10, 5));
        inventario.agregarProducto(new Producto("CL1/32", "Patita Despuntadora", "Izquierdo", 4500, 5, 5));
        inventario.agregarProducto(new Producto("CL1/16N", "Patita Despuntadora", "Izquierdo", 4500, 20, 5));
        inventario.agregarProducto(new Producto("KL25", "Groche de Over", "Inferior", 5000, 20, 5));
        inventario.agregarProducto(new Producto("LP26", "Groche de Over", "Superior", 6000, 20, 5));
        inventario.agregarProducto(new Producto("KL35", "Groche de Over", "Inferior", 5000, 20, 5));
        inventario.agregarProducto(new Producto("LP38", "Groche de Over", "Pesado Superior", 5000, 20, 5));
        inventario.agregarProducto(new Producto("LP225", "Groche de Over", "Pesada M.K", 6000, 20, 5));
        inventario.agregarProducto(new Producto("LP226", "Groche de Over", "Liviana M.K", 6000, 20, 5));
        inventario.agregarProducto(new Producto("KL202", "Groche de Over", "M.K Inferior", 6000, 20, 5));
    }

    private static void agregarProductoAlInventario() {
        String codigo = JOptionPane.showInputDialog("Ingresar codigo del Producto");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
        String descripcion = JOptionPane.showInputDialog("Ingresar descripcion del producto");
        String precio$ = JOptionPane.showInputDialog("Ingrese el precio del producto");
        String cant = JOptionPane.showInputDialog("Ingresar cantidad del producto");
        String stockMinimoStr = JOptionPane.showInputDialog("Ingrese el stock mínimo del producto");

        try {
            double precio = Double.parseDouble(precio$);
            int stock = Integer.parseInt(cant);
            int stockMinimo = Integer.parseInt(stockMinimoStr); 
            Producto producto = new Producto(codigo, nombre, descripcion, precio, stock, stockMinimo);
            inventario.agregarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto agregado.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Datos de precio, stock o stock mínimo inválidos");
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
        } else {
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
            } else {
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
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        }
        Factura factura = new Factura(cliente, pedido);
        factura.imprimirFactura();
    }
}