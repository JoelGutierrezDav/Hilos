package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import BLL.Producto;

public class ControllerProducto {
    private Connection con;

    public ControllerProducto() {
        this.con = Conexion.getInstance().getConnection();
    }

   
    public void agregarProducto(Producto producto) {
        agregarProductoEnBD(producto);
        agregarProductoInventario(producto);
    }

 
    private void agregarProductoEnBD(Producto producto) {
        String sql = "INSERT INTO producto (Codigo, Nombre, Descripcion, Precio, Stock, Stock_minimo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
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


    private void agregarProductoInventario(Producto producto) {
        String sql = "INSERT INTO Inventario (producto_idproducto, ...) VALUES (?, ...)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());
            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar al inventario: " + e.getMessage());
        }
    }


    public void modificarProducto(Producto producto) {
        modificarProductoEnBD(producto);
        actualizarInventario(producto);
    }


    private void modificarProductoEnBD(Producto producto) {
        String sql = "UPDATE producto SET Nombre = ?, Descripcion = ?, Precio = ?, Stock = ?, Stock_minimo = ? WHERE Codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setInt(5, producto.getStockMinimo());
            statement.setString(6, producto.getCodigo());
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Producto modificado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el producto: " + e.getMessage());
        }
    }


    private void actualizarInventario(Producto producto) {
        String sql = "UPDATE Inventario SET ... WHERE producto_idproducto = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());

            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el inventario: " + e.getMessage());
        }
    }


    public void eliminarProducto(String codigo) {
        eliminarProductoEnBD(codigo);
        eliminarProductoInventario(codigo);
    }


    private void eliminarProductoEnBD(String codigo) {
        String sql = "DELETE FROM producto WHERE Codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, codigo);
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
        }
    }

  
    private void eliminarProductoInventario(String codigo) {
        String sql = "DELETE FROM Inventario WHERE producto_idproducto = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar del inventario: " + e.getMessage());
        }
    }

  
    public Producto buscarProductoPorCodigo(String codigo) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE Codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
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


    public LinkedList<Producto> obtenerProductos() {
        LinkedList<Producto> productos = new LinkedList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultados = statement.executeQuery()) {
            while (resultados.next()) {
                Producto producto = new Producto(
                    resultados.getInt("idproducto"),
                    resultados.getString("Codigo"),
                    resultados.getString("Nombre"),
                    resultados.getString("Descripcion"),
                    resultados.getDouble("Precio"),
                    resultados.getInt("Stock"),
                    resultados.getInt("Stock_minimo")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
}
