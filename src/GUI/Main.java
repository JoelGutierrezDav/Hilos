package GUI;

import javax.swing.*;
import java.util.LinkedList;

import BLL.Cliente;
import BLL.Factura;
import BLL.Producto;
import BLL.Pedido;
import BLL.Inventario;
import DLL.ControllerCliente;
import DLL.ControllerFactura;
import DLL.ControllerPedido;

public class Main {
    private static ControllerCliente controllerCliente = new ControllerCliente();
    private static ControllerFactura controllerFactura = new ControllerFactura();
    private static ControllerPedido controllerPedido = new ControllerPedido();
    private static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        String[] opciones = {"Gestión de Clientes", "Gestión de Productos", "Gestión de Pedidos", "Gestión de Facturas", "Inventario", "Salir"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, "--- SISTEMA DE GESTIÓN ---", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    menuClientes();
                    break;
                case 1:
                    menuProductos();
                    break;
                case 2:
                    menuPedidos();
                    break;
                case 3:
                    menuFacturas();
                    break;
                case 4:
                    menuInventario();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void menuClientes() {
        String[] opciones = {"Agregar Cliente", "Listar Clientes", "Buscar Cliente", "Actualizar Cliente", "Eliminar Cliente", "Volver"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, "--- GESTIÓN DE CLIENTES ---", "Menú Clientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    agregarCliente();
                    break;
                case 1:
                    listarClientes();
                    break;
                case 2:
                    buscarCliente();
                    break;
                case 3:
                    actualizarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            Cliente nuevoCliente = new Cliente(nombre);
            controllerCliente.crearCliente(nuevoCliente);
        } else {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
        }
    }

    private static void listarClientes() {
        LinkedList<Cliente> clientes = controllerCliente.mostrarClientes();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
        } else {
            StringBuilder sb = new StringBuilder("--- LISTA DE CLIENTES ---\n");
            for (Cliente cliente : clientes) {
                sb.append(cliente).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void buscarCliente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Cliente cliente = controllerCliente.buscarCliente(id);
                if (cliente != null) {
                    JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    }

    private static void actualizarCliente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente a actualizar:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    Cliente clienteActualizado = new Cliente(id, nuevoNombre);
                    controllerCliente.actualizarCliente(clienteActualizado);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    }

    private static void eliminarCliente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente a eliminar:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                controllerCliente.eliminarCliente(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    }

    private static void menuProductos() {
        String[] opciones = {"Listar Productos", "Buscar Producto por Código", "Agregar Producto", "Aumentar Stock", "Verificar Stock", "Volver"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, "--- GESTIÓN DE PRODUCTOS ---", "Menú Productos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    listarProductos();
                    break;
                case 1:
                    buscarProducto();
                    break;
                case 2:
                    agregarProducto();
                    break;
                case 3:
                    aumentarStock();
                    break;
                case 4:
                    verificarStock();
                    break;
                case 5:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void listarProductos() {
        LinkedList<Producto> productos = inventario.getProductos();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados.");
        } else {
            StringBuilder sb = new StringBuilder("--- LISTA DE PRODUCTOS ---\n");
            for (Producto producto : productos) {
                sb.append(producto).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void buscarProducto() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
        if (codigo != null) {
            Producto producto = inventario.buscarProductoPorCodigo(codigo);
            if (producto != null) {
                JOptionPane.showMessageDialog(null, "Producto encontrado: " + producto);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        }
    }

    private static void agregarProducto() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción:");
        String precioStr = JOptionPane.showInputDialog("Ingrese el precio:");
        String stockStr = JOptionPane.showInputDialog("Ingrese la cantidad en stock:");
        String stockMinimoStr = JOptionPane.showInputDialog("Ingrese el stock mínimo:");

        if (codigo != null && nombre != null && descripcion != null && precioStr != null && stockStr != null && stockMinimoStr != null) {
            try {
                double precio = Double.parseDouble(precioStr);
                int stock = Integer.parseInt(stockStr);
                int stockMinimo = Integer.parseInt(stockMinimoStr);
                Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, stock, stockMinimo);
                inventario.agregarProducto(nuevoProducto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Precio o cantidad inválidos.");
            }
        }
    }

    private static void aumentarStock() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a aumentar:");
        if (codigo != null && cantidadStr != null) {
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                inventario.aumentarStockProducto(codigo, cantidad);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida.");
            }
        }
    }

    private static void verificarStock() {
        inventario.verificarStock();
    }

    private static void menuPedidos() {
        String[] opciones = {"Agregar Pedido", "Listar Pedidos", "Buscar Pedido", "Actualizar Pedido", "Eliminar Pedido", "Volver"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, "--- GESTIÓN DE PEDIDOS ---", "Menú Pedidos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    agregarPedido();
                    break;
                case 1:
                    listarPedidos();
                    break;
                case 2:
                    buscarPedido();
                    break;
                case 3:
                    actualizarPedido();
                    break;
                case 4:
                    eliminarPedido();
                    break;
                case 5:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void agregarPedido() {
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha del pedido (YYYY-MM-DD):");
        String totalStr = JOptionPane.showInputDialog("Ingrese el total del pedido:");
        String codigoProducto = JOptionPane.showInputDialog("Ingrese el código del producto:");
        String idFacturaStr = JOptionPane.showInputDialog("Ingrese el ID de la factura:");
        String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del cliente:");

        if (fecha != null && totalStr != null && codigoProducto != null && idFacturaStr != null && idClienteStr != null) {
            try {
                double total = Double.parseDouble(totalStr);
                int idFactura = Integer.parseInt(idFacturaStr);
                int idCliente = Integer.parseInt(idClienteStr);
                Pedido nuevoPedido = new Pedido(fecha, total, codigoProducto, idFactura, idCliente);
                controllerPedido.agregarPedido(nuevoPedido);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Total, ID de factura o ID de cliente inválidos.");
            }
        }
    }

    private static void listarPedidos() {
        LinkedList<Pedido> pedidos = controllerPedido.mostrarPedidos();
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos registrados.");
        } else {
            StringBuilder sb = new StringBuilder("--- LISTA DE PEDIDOS ---\n");
            for (Pedido pedido : pedidos) {
                sb.append(pedido).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void buscarPedido() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del pedido:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Pedido pedido = controllerPedido.buscarPedido(id);
                if (pedido != null) {
                    JOptionPane.showMessageDialog(null, "Pedido encontrado: " + pedido);
                } else {
                    JOptionPane.showMessageDialog(null, "Pedido no encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    }

    private static void actualizarPedido() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del pedido a actualizar:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String nuevaFecha = JOptionPane.showInputDialog("Ingrese la nueva fecha (YYYY-MM-DD):");
                String nuevoTotalStr = JOptionPane.showInputDialog("Ingrese el nuevo total:");
                String nuevoCodigoProducto = JOptionPane.showInputDialog("Ingrese el nuevo código del producto:");
                String nuevoIdFacturaStr = JOptionPane.showInputDialog("Ingrese el nuevo ID de la factura:");
                String nuevoIdClienteStr = JOptionPane.showInputDialog("Ingrese el nuevo ID del cliente:");

                if (nuevaFecha != null && nuevoTotalStr != null && nuevoCodigoProducto != null && nuevoIdFacturaStr != null && nuevoIdClienteStr != null) {
                    double nuevoTotal = Double.parseDouble(nuevoTotalStr);
                    int nuevoIdFactura = Integer.parseInt(nuevoIdFacturaStr);
                    int nuevoIdCliente = Integer.parseInt(nuevoIdClienteStr);
                    Pedido pedidoActualizado = new Pedido(id, nuevaFecha, nuevoTotal, nuevoCodigoProducto, nuevoIdFactura, nuevoIdCliente);
                    controllerPedido.modificarPedido(id, pedidoActualizado);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID, total o datos inválidos.");
            }
        }
    }

    private static void eliminarPedido() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del pedido a eliminar:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                controllerPedido.eliminarPedido(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    }

    private static void menuFacturas() {
        String[] opciones = {"Crear Factura", "Listar Facturas", "Volver"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, " GESTIÓN DE FACTURAS ", "Menú Facturas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    crearFactura();
                    break; 
                case 1:
                    listarFacturas();
                    break;
                case 2:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void crearFactura() {
        String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
        if (idClienteStr != null) {
            try {
                int idCliente = Integer.parseInt(idClienteStr);
                String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de productos:");
                if (cantidadStr != null) {
                    int cantidadProductos = Integer.parseInt(cantidadStr);
                    LinkedList<Producto> productos = new LinkedList<>();
                    LinkedList<Integer> cantidades = new LinkedList<>();

                    for (int i = 0; i < cantidadProductos; i++) {
                        String codigoProducto = JOptionPane.showInputDialog("Ingrese el código del producto " + (i + 1) + ":");
                        Producto producto = inventario.buscarProductoPorCodigo(codigoProducto);
                        if (producto != null) {
                            productos.add(producto);
                            String cantidadProductoStr = JOptionPane.showInputDialog("Ingrese la cantidad de " + producto.getNombre() + ":");
                            if (cantidadProductoStr != null) {
                                int cantidad = Integer.parseInt(cantidadProductoStr);
                                cantidades.add(cantidad);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                            i--;
                        }
                    }

                    
                    Cliente cliente = new Cliente(idCliente, "Nombre del Cliente"); 
                    controllerFactura.crearFactura(cliente, productos, cantidades);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID de cliente o cantidad inválidos.");
            }
        }
    }

    private static void listarFacturas() {
        LinkedList<Factura> facturas = (LinkedList<Factura>) controllerFactura.mostrarFacturas();
        if (facturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay facturas registradas.");
        } else {
            StringBuilder sb = new StringBuilder(" LISTA DE FACTURAS \n");
            for (Factura factura : facturas) {
                sb.append(factura).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
    private static void menuInventario() {
        String[] opciones = {"Listar Productos", "Verificar Stock", "Volver"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, " GESTIÓN DE INVENTARIO ", "Menú Inventario",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    listarProductos(); 
                    break;
                case 1:
                    verificarStock(); 
                    break;
                case 2:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }
}