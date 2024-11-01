package GUI;
import javax.swing.JOptionPane;

import BLL.Cliente;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            String menuPrincipal = JOptionPane.showInputDialog(null,
                    "Menú Principal\n"
                    + "1. Gestión de Clientes\n"
                    + "2. Gestión de Pedidos Proveedor\n"
                    + "3. Gestión de Facturas\n"
                    + "4. Gestión de Inventario\n"
                    + "5. Historial\n"
                    + "6. Salir");

            switch (menuPrincipal) {
                case "1":
                    gestionarClientes();
                    break;
                case "2":
                    gestionarPedidosProveedor();
                    break;
                case "3":
                    gestionarFacturas();
                    break;
                case "4":
                    gestionarInventario();
                    break;
                case "5":
                    mostrarHistorial();
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }

    // Método para gestionar clientes
    private static void gestionarClientes() {
        String menuClientes = JOptionPane.showInputDialog(null,
                "Gestión de Clientes\n"
                + "1. Agregar Cliente\n"
                + "2. Mostrar Clientes\n"
                + "3. Buscar Cliente\n"
                + "4. Eliminar Cliente\n"
                + "5. Volver");

        switch (menuClientes) {
            case "1":
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                Cliente cliente = new Cliente(nombre);
                Cliente.crearCliente(cliente);
                break;
            case "2":
                LinkedList<Cliente> clientes = Cliente.mostrarClientes();
                StringBuilder sbClientes = new StringBuilder("Clientes:\n");
                for (Cliente c : clientes) {
                    sbClientes.append(c.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbClientes.toString());
                break;
            case "3":
                int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a buscar:"));
                Cliente buscado = Cliente.buscarCliente(idBuscar);
                JOptionPane.showMessageDialog(null, buscado != null ? buscado.toString() : "Cliente no encontrado.");
                break;
            case "4":
                int idEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a eliminar:"));
                Cliente.eliminarCliente(idEliminar);
                break;
            case "5":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    // Método para gestionar pedidos de proveedor
    private static void gestionarPedidosProveedor() {
        String menuPedidos = JOptionPane.showInputDialog(null,
                "Gestión de Pedidos a Proveedores\n"
                + "1. Agregar Pedido\n"
                + "2. Mostrar Pedidos\n"
                + "3. Buscar Pedido\n"
                + "4. Actualizar Pedido\n"
                + "5. Eliminar Pedido\n"
                + "6. Volver");

        switch (menuPedidos) {
            case "1":
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del pedido:");
                String proveedor = JOptionPane.showInputDialog("Ingrese el nombre del proveedor:");
                PedidoProveedor pedido = new PedidoProveedor(descripcion, proveedor);
                PedidoProveedor.crearPedidoProveedor(pedido);
                break;
            case "2":
                LinkedList<PedidoProveedor> pedidos = PedidoProveedor.mostrarPedidoProveedor();
                StringBuilder sbPedidos = new StringBuilder("Pedidos de Proveedores:\n");
                for (PedidoProveedor p : pedidos) {
                    sbPedidos.append(p.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbPedidos.toString());
                break;
            case "3":
                int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a buscar:"));
                PedidoProveedor pedidoBuscado = PedidoProveedor.buscarPedidoProveedor(idBuscar);
                JOptionPane.showMessageDialog(null, pedidoBuscado != null ? pedidoBuscado.toString() : "Pedido no encontrado.");
                break;
            case "4":
                int idActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a actualizar:"));
                String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción del pedido:");
                String nuevoProveedor = JOptionPane.showInputDialog("Ingrese el nuevo nombre del proveedor:");
                PedidoProveedor pedidoActualizado = new PedidoProveedor(idActualizar, nuevaDescripcion, nuevoProveedor);
                PedidoProveedor.actualizarPedidoProveedor(pedidoActualizado);
                break;
            case "5":
                int idEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a eliminar:"));
                PedidoProveedor.eliminarPedidoProveedor(idEliminar);
                break;
            case "6":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    // Método para gestionar facturas
    private static void gestionarFacturas() {
        String menuFacturas = JOptionPane.showInputDialog(null,
                "Gestión de Facturas\n"
                + "1. Crear Factura\n"
                + "2. Mostrar Facturas\n"
                + "3. Volver");

        switch (menuFacturas) {
            case "1":
                // Implementa la lógica para crear una nueva factura
                String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                Factura nuevaFactura = new Factura(nombreCliente);
                Factura.crearFactura(nuevaFactura);
                break;
            case "2":
                LinkedList<Factura> facturas = HistorialFacturas.mostrarFacturas();
                StringBuilder sbFacturas = new StringBuilder("Facturas:\n");
                for (Factura f : facturas) {
                    sbFacturas.append(f.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbFacturas.toString());
                break;
            case "3":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    // Método para gestionar inventario
    private static void gestionarInventario() {
        String menuInventario = JOptionPane.showInputDialog(null,
                "Gestión de Inventario\n"
                + "1. Agregar Producto\n"
                + "2. Mostrar Productos\n"
                + "3. Actualizar Stock\n"
                + "4. Eliminar Producto\n"
                + "5. Verificar Stock\n"
                + "6. Volver");

        switch (menuInventario) {
            case "1":
                String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del producto:");
                double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));
                Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, cantidad);
                Inventario.agregarProducto(nuevoProducto);
                break;
            case "2":
                LinkedList<Producto> productos = Inventario.mostrarProductos();
                StringBuilder sbProductos = new StringBuilder("Productos:\n");
                for (Producto p : productos) {
                    sbProductos.append(p.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbProductos.toString());
                break;
            case "3":
                int idActualizarStock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto a actualizar:"));
                int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del stock:"));
                Inventario.actualizarStock(idActualizarStock, nuevoStock);
                break;
            case "4":
                int idEliminarProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto a eliminar:"));
                Inventario.eliminarProducto(idEliminarProducto);
                break;
            case "5":
                Inventario.verificarStock();
                break;
            case "6":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    // Método para mostrar historial de facturas y pedidos
    private static void mostrarHistorial() {
        String menuHistorial = JOptionPane.showInputDialog(null,
                "Historial\n"
                + "1. Mostrar Facturas\n"
                + "2. Mostrar Pedidos Proveedor\n"
                + "3. Volver");

        switch (menuHistorial) {
            case "1":
                LinkedList<Factura> facturas = HistorialFacturas.mostrarFacturas();
                StringBuilder sbFacturas = new StringBuilder("Facturas:\n");
                for (Factura f : facturas) {
                    sbFacturas.append(f.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbFacturas.toString());
                break;
            case "2":
                LinkedList<PedidoProveedor> pedidos = HistorialPedidos.mostrarPedidos();
                StringBuilder sbPedidos = new StringBuilder("Pedidos:\n");
                for (PedidoProveedor p : pedidos) {
                    sbPedidos.append(p.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbPedidos.toString());
                break;
            case "3":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }
}
