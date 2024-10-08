import javax.swing.JOptionPane;

public class Main {
    private static Inventario inventario = new Inventario();
    private static HistorialFacturas historialFacturas = new HistorialFacturas();
    private static HistorialPedidos historialPedidos = new HistorialPedidos();
    
    /*boolean salir = false;*/
    
    public static void main(String[] args) {
    	cargarProductosPredefinidos();
    	

        while (true) {
            String[] opciones = {"Agregar productos", "Mostrar productos", "Crear pedido Proveedor", "Crear factura", "Verificar stock", "Mostrar Facturas", "Mostrar Pedidos", "Mostrar Productos más vendidos","Modificar Datos Productos","Salir"};
            String opcion = (String)JOptionPane.showInputDialog(null, "Elija una opcion: ", "Menu Principal", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

            switch (opcion) {
                case "Agregar productos":
                    agregarProductoAlInventario();
                    break;
                case  "Mostrar productos":
                    mostrarProductos();
                    break;
                case "Crear pedido Proveedor":
                    crearPedidoProveedor();
                    break;
                case "Crear factura":
                    crearFactura();
                    break;
                case  "Verificar stock":
                    inventario.verificarStock();
                    break;
                case "Mostrar Facturas":
                    historialFacturas.mostrarFacturas(); 
                    break;
                case "Mostrar Pedidos":
                    historialPedidos.mostrarPedidos();
                    break;
                case "Mostrar Productos más vendidos":
                	mostrarProductosMasVendidos();
                	break;
                case "Modificar Datos Productos":
                	modificarDatosProducto();
                	break;
                case "Salir":
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
				String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a aumentar");
		    	JOptionPane.showMessageDialog(null, "Stock actualizado del producto: " + codigo);
		    	try {
					int cantidad = Integer.parseInt(cantidadStr);
					inventario.aumentarStockProducto(codigo, cantidad);
				} catch ( NumberFormatException e) {
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
    }
    }