import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
public class HistorialPedidos {
	private List<Pedido> pedidosRealizados;
	
	public HistorialPedidos() {
		pedidosRealizados = new ArrayList<>();
	}
	
	public void agregarPedido(Pedido pedido) {
		pedidosRealizados.add(pedido);
	}
	
	public List<Pedido> getPedidosRealizados(){
		return pedidosRealizados;
	}
	
	public void mostrarPedidos() {
		StringBuilder historial = new StringBuilder("Historial de pedidos: \n");
		for (Pedido pedido : pedidosRealizados) {
			historial.append(pedido.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, historial.toString(), "Historial de Pedidos", JOptionPane.INFORMATION_MESSAGE);
	}
}
