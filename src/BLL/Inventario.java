package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.Conexion;

public class Inventario {
    private static Connection con = Conexion.getInstance().getConnection();

    public LinkedList<Producto> getProductos() {
        LinkedList<Producto> productos = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM producto");
            ResultSet resultados = statement.executeQuery();
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

                if (producto.getStock() < producto.getStockMinimo()) {
                    JOptionPane.showMessageDialog(null, "Alerta: El producto " + producto.getNombre() + " tiene un stock bajo.");
                }
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

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
        }
    }

    public void aumentarStockProducto(String codigo, int cantidad) {
        try {
            Producto producto = buscarProductoPorCodigo(codigo);
            if (producto != null) {
                int nuevoStock = producto.getStock() + cantidad;
                PreparedStatement statement = con.prepareStatement("UPDATE producto SET Stock = ? WHERE Codigo = ?");
                statement.setInt(1, nuevoStock);
                statement.setString(2, codigo);

                int filas = statement.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + e.getMessage());
        }
    }

    public void verificarStock() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM producto WHERE Stock < Stock_minimo");
            ResultSet resultados = statement.executeQuery();
            StringBuilder productosBajoStock = new StringBuilder();
            while (resultados.next()) {
                productosBajoStock.append("Código: ")
                    .append(resultados.getString("Codigo"))
                    .append(", Nombre: ")
                    .append(resultados.getString("Nombre"))
                    .append(", Stock: ")
                    .append(resultados.getInt("Stock"))
                    .append("\n");
            }

            if (productosBajoStock.length() == 0) {
                JOptionPane.showMessageDialog (null, "No hay productos con stock bajo.");
            } else {
                JOptionPane.showMessageDialog(null, "Productos con stock bajo:\n" + productosBajoStock.toString());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el stock: " + e.getMessage());
        }
    }

    public void modificarProducto(Producto producto) {
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
}