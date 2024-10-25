import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private int id;
    private static Connection con = (Connection) Conexion.getInstance().getConnection();
    private static final int STOCK_MINIMO = 5;

    public Producto(int id, String codigo, String nombre, String descripcion, double precio, int stock, int stockMinimo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, int stock, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }

    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }

    public boolean esBajoEnStock() {
        return stock < STOCK_MINIMO;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | " + nombre + " - $" + precio + " | Stock: " + stock + " | Descripción: " + descripcion;
    }

    public static void crearProducto(Producto producto) {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO `producto`(`Codigo`, `Nombre`, `Descripcion`, `Precio`, `Stock`, `Stock_minimo`) VALUES (?,?,?,?,?,?)");
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getStock());
            statement.setInt(6, producto.getStockMinimo());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Se guardó con éxito!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto!");
        }
    }

    public static LinkedList<Producto> mostrarProducto() {
        LinkedList<Producto> productos = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `producto`");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                productos.add(new Producto(resultados.getInt("id"),
                        resultados.getString("Codigo"),
                        resultados.getString("Nombre"),
                        resultados.getString("Descripcion"),
                        resultados.getDouble("Precio"),
                        resultados.getInt("Stock"),
                        resultados.getInt("Stock_minimo")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los productos!");
        }
        return productos;
    }

    public static boolean actualizarProducto(Producto producto) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE `producto` SET `Codigo`= ?, `Nombre`= ?, `Descripcion`= ?, `Precio`= ?, `Stock`= ?, `Stock_minimo`= ? WHERE id = ?");
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getStock());
            statement.setInt(6, producto.getStockMinimo());
            statement.setInt(7, producto.getId());

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Producto actualizado!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto!");
        }
        return false;
    }

    public static Producto buscarProducto(int id) {
        Producto producto = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `producto` WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                producto = new Producto(resultados.getInt("id"),
                        resultados.getString("Codigo"),
                        resultados.getString("Nombre"),
                        resultados.getString("Descripcion"),
                        resultados.getDouble("Precio"),
                        resultados.getInt("Stock"),
                        resultados.getInt("Stock_minimo"));
            }
            return producto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el producto!");
        }
        return producto;
    }

    public static boolean eliminarProducto(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `producto` WHERE id = ?");
            statement.setInt(1, id);

            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto!");
        }
        return false;
    }
}

