package DLL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import BLL.Factura;
import BLL.Cliente;
import BLL.Producto;

public class ControllerFactura {
    private Connection con;

    public ControllerFactura() {
        this.con = Conexion.getInstance().getConnection();
    }

    public void crearFactura(Cliente cliente, List<Producto> productos, List<Integer> cantidades) {
        double total = calcularTotal(productos, cantidades);
        LocalDate fecha = LocalDate.now();

        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO factura (Fecha, Total, cliente_idcliente) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setDate(1, Date.valueOf(fecha));
            statement.setDouble(2, total);
            statement.setInt(3, cliente.getId());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int facturaId = generatedKeys.getInt(1);
                    guardarDetallesFactura(facturaId, productos, cantidades);
                    JOptionPane.showMessageDialog(null, "Factura creada con éxito.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la factura: " + e.getMessage());
        }
    }

    private double calcularTotal(List<Producto> productos, List<Integer> cantidades) {
        double total = 0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    private void guardarDetallesFactura(int facturaId, List<Producto> productos, List<Integer> cantidades) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
            "INSERT INTO pedido (Fecha_Pedido, Total, producto_idproducto, factura_idfactura, cliente_idcliente) VALUES (?, ?, ?, ?, ?)"
        );
        for (int i = 0; i < productos.size(); i++) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setDouble(2, productos.get(i).getPrecio() * cantidades.get(i));
            statement.setInt(3, productos.get(i).getId());
            statement.setInt(4, facturaId);
            statement.setInt(5, 1); 
            statement.addBatch();
        }
        statement.executeBatch();
    }


    public List<Factura> mostrarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM factura");
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt("idfactura");
                LocalDate fecha = resultados.getDate("Fecha").toLocalDate();
                double total = resultados.getDouble("Total");
                int clienteId = resultados.getInt("cliente_idcliente");
                facturas.add(new Factura(id, fecha, total, clienteId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar facturas: " + e.getMessage());
        }
        return facturas;
    }
}