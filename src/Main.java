import javax.swing.JOptionPane;

public class Main {
    private static Inventario inventario = new Inventario();
    private static HistorialFacturas historialFacturas = new HistorialFacturas();
    private static HistorialPedidos historialPedidos = new HistorialPedidos();
    
    boolean salir = false;
    
    public static void main(String[] args) {
    	/*cargarProductosPredefinidos();*/
    	
    	while (!salir) {
            String menuPrincipal = JOptionPane.showInputDialog(
                    "Menú Principal:\n" +
                    "1. Productos\n" +
                    "2. Crear\n" +
                    "3. Historial\n" +
                    "4. Salir");

            switch (menuPrincipal) {
                case "1":
                    String menuProductos = JOptionPane.showInputDialog(
                            "Productos:\n" +
                            "1. Agregar productos\n" +
                            "2. Mostrar productos\n" +
                            "3. Verificar Stock\n" +
                            "4. Aumentar Stock\n" +
                            "5. Modificar Producto");

                    switch (menuProductos) {
                        case "1":
                            String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
                            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                            String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del producto:");
                            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad:"));
                            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));

                            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, cantidad, precio);
                            inventario.agregarProducto(nuevoProducto);
                            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");
                            break;

                        case "2":
                            StringBuilder productos = new StringBuilder("Productos:\n");
                            for (Producto producto : inventario.getProductos()) {
                                productos.append("Producto: ").append(producto.getNombre())
                                         .append(" - Stock: ").append(producto.getCantidad())
                                         .append(" - Precio: ").append(producto.getPrecio()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, productos.toString());
                            break;

                        case "3":
                            String codigoVerificar = JOptionPane.showInputDialog("Ingrese el código del producto para verificar el stock:");
                            Producto productoVerificar = inventario.buscarProducto(codigoVerificar);
                            if (productoVerificar != null) {
                                JOptionPane.showMessageDialog(null, "El producto " + productoVerificar.getNombre() + " tiene " +
                                        productoVerificar.getCantidad() + " unidades en stock.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                            }
                            break;

                        case "4":
                            String codigoAumentar = JOptionPane.showInputDialog("Ingrese el código del producto para aumentar el stock:");
                            Producto productoAumentar = inventario.buscarProducto(codigoAumentar);
                            if (productoAumentar != null) {
                                int cantidadAumentar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a aumentar:"));
                                productoAumentar.aumentarStock(cantidadAumentar);
                                JOptionPane.showMessageDialog(null, "El stock del producto " + productoAumentar.getNombre() +
                                        " ha sido actualizado. Nuevo stock: " + productoAumentar.getCantidad());
                            } else {
                                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                            }
                            break;

                        case "5":
                            String codigoModificar = JOptionPane.showInputDialog("Ingrese el código del producto a modificar:");
                            Producto productoModificar = inventario.buscarProducto(codigoModificar);
                            if (productoModificar != null) {
                                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:", productoModificar.getNombre());
                                String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción:", productoModificar.getDescripcion());
                                int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:", productoModificar.getCantidad()));
                                double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio:", productoModificar.getPrecio()));

                                productoModificar.setNombre(nuevoNombre);
                                productoModificar.setDescripcion(nuevaDescripcion);
                                productoModificar.setCantidad(nuevaCantidad);
                                productoModificar.setPrecio(nuevoPrecio);

                                JOptionPane.showMessageDialog(null, "Producto modificado con éxito.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                    break;

                case "2": 
                    String menuCrear = JOptionPane.showInputDialog(
                            "Crear:\n" +
                            "1. Crear pedido proveedor\n" +
                            "2. Crear factura");

                    switch (menuCrear) {
                        case "1":
                            PedidoProveedor pedidoProveedor = new PedidoProveedor();
                            String codigoPedido = JOptionPane.showInputDialog("Ingrese el código del producto a pedir:");
                            Producto productoPedido = inventario.buscarProducto(codigoPedido);
                            if (productoPedido != null) {
                                int cantidadPedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a pedir:"));
                                pedidoProveedor.agregarProducto(productoPedido, cantidadPedido);
                                historialPedidos.agregarPedido(pedidoProveedor);
                                JOptionPane.showMessageDialog(null, "Pedido creado con éxito.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                            }
                            break;

                        case "2":
                            String clienteNombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                            Cliente cliente = new Cliente(clienteNombre);
                            Factura factura = new Factura(cliente);

                            boolean agregarProductos = true;
                            while (agregarProductos) {
                                String codigoFactura = JOptionPane.showInputDialog("Ingrese el código del producto a facturar:");
                                Producto productoFactura = inventario.buscarProducto(codigoFactura);
                                if (productoFactura != null) {
                                    int cantidadFactura = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));
                                    if (productoFactura.getCantidad() >= cantidadFactura) {
                                        factura.agregarProducto(productoFactura, cantidadFactura);
                                        productoFactura.reducirStock(cantidadFactura);
                                        JOptionPane.showMessageDialog(null, "Producto agregado a la factura.");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Stock insuficiente.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                                }

                                int continuar = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto?", "Agregar producto", JOptionPane.YES_NO_OPTION);
                                agregarProductos = (continuar == JOptionPane.YES_OPTION);
                            }

                            historialFacturas.agregarFactura(factura);
                            JOptionPane.showMessageDialog(null, "Factura creada con éxito.");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                    break;

                case "3": 
                    String menuHistorial = JOptionPane.showInputDialog(
                            "Historial:\n" +
                            "1. Mostrar facturas\n" +
                            "2. Mostrar pedidos\n" +
                            "3. Mostrar productos más vendidos");

                    switch (menuHistorial) {
                        case "1":
                            StringBuilder facturas = new StringBuilder("Facturas:\n");
                            for (Factura f : historialFacturas.getFacturas()) {
                                facturas.append("Cliente: ").append(f.getCliente().getNombre())
                                        .append(" - Total: ").append(f.calcularTotal()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, facturas.toString());
                            break;

                        case "2":
                            StringBuilder pedidos = new StringBuilder("Pedidos:\n");
                            for (Pedido p : historialPedidos.getPedidos()) {
                                pedidos.append("Pedido con ").append(p.getProductos().size()).append(" productos.\n");
                            }
                            JOptionPane.showMessageDialog(null, pedidos.toString());
                            break;

                        case "3":
                            historialFacturas.mostrarProductosMasVendidos();
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                    break;

                case "4":
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}
        /*while (true) {
            String[] opciones = {"Agregar productos", "Mostrar productos", "Crear pedido Proveedor", "Crear factura", "Verificar stock", "Mostrar Facturas", "Mostrar Pedidos", "Aumentar stock","Mostrar Productos más vendidos","Modificar Datos Productos","Salir"};
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
                	aumentarStock();
                	break;
                case 8:
                	mostrarProductosMasVendidos();
                	break;
                case 9:
                	modificarDatosProducto();
                	break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Saliendo");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no valida");
            }
        }*/
    }
    
    /*private static void cargarProductosPredefinidos() {
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
    }*/

    /*private static void agregarProductoAlInventario() {
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
    }*/

    /*private static void mostrarProductos() {
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
    	Pedido pedido = new Pedido();

        while (true) {
            String codigo_producto = JOptionPane.showInputDialog("Ingrese el código del producto para reponer (o 'fin' para terminar):");
            if (codigo_producto.equalsIgnoreCase("fin")) {
                break;
            }

            Producto producto = inventario.buscarProductoPorCodigo(codigo_producto);
            if (producto != null) {
                String cantidad_str = JOptionPane.showInputDialog("Ingrese la cantidad de " + producto.getNombre() + " a pedir:");
                try {
                    int cantidad = Integer.parseInt(cantidad_str);
                    pedido.agregarProducto(producto, cantidad);
                    JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        }

        if (!pedido.getProductos().isEmpty()) {
            historialPedidos.agregarPedido(pedido); 
            JOptionPane.showMessageDialog(null, "Pedido creado y guardado en el historial.");
        } else {
            JOptionPane.showMessageDialog(null, "No se agregaron productos al pedido.");
        }
    }

    private static void crearFactura() {
        Pedido pedido = new Pedido();
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        Cliente cliente = new Cliente(nombreCliente);

        while (true) {
            String codigoProducto = JOptionPane.showInputDialog("Ingrese el código del producto (o 'fin' para terminar):");
            if (codigoProducto.equalsIgnoreCase("fin")) {
                break;
            }

            Producto producto = inventario.buscarProductoPorCodigo(codigoProducto);
            if (producto != null) {
                String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de " + producto.getNombre() + " a comprar:");
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= producto.getStock()) {
                        producto.reducirStock(cantidad);
                        pedido.agregarProducto(producto, cantidad);
                        JOptionPane.showMessageDialog(null, "Producto agregado a la factura.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay suficiente stock. Stock actual: " + producto.getStock());
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        }

        if (!pedido.getProductos().isEmpty()) {
            Factura factura = new Factura(cliente, pedido);
            factura.imprimirFactura();
            historialFacturas.agregarFactura(factura);
        } else {
            JOptionPane.showMessageDialog(null, "No se agregaron productos a la factura.");
        }
    }
    private static void aumentarStock() {
    	String codigo = JOptionPane.showInputDialog("Ingrese el codigo del producto  para aumentar stoock:");
    	String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a aumentar");
    	JOptionPane.showMessageDialog(null, "Stock actualizado del producto: " + codigo);
    	try {
			int cantidad = Integer.parseInt(cantidadStr);
			inventario.aumentarStockProducto(codigo, cantidad);
		} catch ( NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Cantidad invalida");
		}
    }
    private static void mostrarProductosMasVendidos() {
    	historialFacturas.mostrarProductosMasVendidos();
    }
    private static void modificarDatosProducto() {
    	String codigo =  JOptionPane.showInputDialog("Ingrese el codigo del producto: ");
    	Producto producto = inventario.buscarProductoPorCodigo(codigo);
    	
    	if (producto != null) {
			String[] opciones = {"Nombre", "Descripcion", "Precio", "Cantidad"};
			int opcion = JOptionPane.showOptionDialog(null, "Elija una opcion: ", "Modificar Producto", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
			
			switch (opcion) {
			case 0:
				String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
				producto.setNombre(nuevoNombre);
				break;
			case 1:
				String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripcion");
				producto.setDescripcion(nuevaDescripcion);
				break;
			case 2:
				String nuevoPrecio = JOptionPane.showInputDialog("Ingrese nuevo precio");
				try {
					double precio = Double.parseDouble(nuevoPrecio);
					producto.setPrecio(precio);
				} catch ( NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Precio Invalido");
				}
				break;
			case 3:
				String nuevaCantidad = JOptionPane.showInputDialog("Ingrese cantidad");
				try {
					int cantidad = Integer.parseInt(nuevaCantidad);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Cantidad invalida");
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcion no valida");
				break;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Producto no encontrado");
		}
    }*/
    }