package DLL;

import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Producto;
import BLL.Inventario;

public class ControllerProducto {
    private Connection con;
    private Inventario inventario;

    public ControllerProducto() {
        this.con = Conexion.getInstance().getConnection();
        this.inventario = new Inventario();
    }

    public void agregarProducto(Producto producto) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO producto (Codigo, Nombre, Descripcion, Precio, Stock, Stock_minimo) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getStock());
            statement.setInt(6, producto.getStockMinimo());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
        }
    }

    public LinkedList<Producto> obtenerProductos() {
        LinkedList<Producto> productos = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM producto");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                productos.add(new Producto(
                    resultados.getInt("idproducto"),
                    resultados.getString("Codigo"),
                    resultados.getString("Nombre"),
                    resultados.getString("Descripcion"),
                    resultados.getDouble("Precio"),
                    resultados.getInt("Stock"),
                    resultados.getInt("Stock_minimo")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        Producto producto = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM producto WHERE Codigo = ?");
            statement.setString(1, codigo);
            ResultSet resultados = statement.executeQuery();
            if (resultados.next()) {
                producto = new Producto(
                    resultados.getInt("idproducto"),
                    resultados.getString("Codigo"),
                    resultados.getString("Nombre"),
                    resultados.getString("Descripcion"),
                    resultados.getDouble("Precio"),
                    resultados.getInt("Stock"),
                    resultados.getInt("Stock_minimo")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
        }
        return producto;
    }

    public boolean modificarProducto(Producto producto) { 
        try { 
            PreparedStatement statement = con.prepareStatement( 
                "UPDATE producto SET Nombre = ?, Descripcion = ?, Precio = ?, Stock = ?, Stock_minimo = ? WHERE Codigo = ?" 
            ); 
            statement.setString(1, producto.getNombre()); 
            statement.setString(2, producto.getDescripcion()); 
            statement.setDouble(3, producto.getPrecio()); 
            statement.setInt(4, producto.getStock()); 
            statement.setInt(5, producto.getStockMinimo()); 
            statement.setString(6, producto.getCodigo()); 

            int filasAfectadas = statement.executeUpdate(); 
            if (filasAfectadas > 0) { 
                JOptionPane.showMessageDialog(null, "Producto modificado exitosamente. "); 
                return true; 
            } else { 
                JOptionPane.showMessageDialog(null, "No se encontró el producto. "); 
            } 
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error al modificar el producto: " + e.getMessage()); 
        } 
        return false; 
    }

    public boolean eliminarProducto(String codigo) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM producto WHERE Codigo = ?");
            statement.setString(1, codigo);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
        }
        return false;
    }
    public LinkedList<Producto> obtenerProductosBajoStockMinimo() {
        return inventario.obtenerProductosBajoStockMinimo();
    }
}