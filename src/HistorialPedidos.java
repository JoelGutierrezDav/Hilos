import java.util.ArrayList;
import javax.swing.JOptionPane;
public class HistorialPedidos {
    private ArrayList<Pedido> pedidos;

    public HistorialPedidos() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : pedidos) {
            sb.append(pedido.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }
}
