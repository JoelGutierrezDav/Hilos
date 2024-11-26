package GUI;

import javax.swing.*;
import DLL.ControllerPedido;
import BLL.Pedido;
import java.util.LinkedList;

public class VistaListaPedidos extends JPanel {
    private ControllerPedido controllerPedido;

    public VistaListaPedidos(ControllerPedido controllerPedido) {
        this.controllerPedido = controllerPedido;
        initialize();
    }

    private void initialize() {
        setLayout(null); 

        JButton btnListar = new JButton("Listar Pedidos");
        btnListar.setBounds(10, 20, 255, 25);
        btnListar.addActionListener(e -> listarPedidos());
        add(btnListar);
    }

    private void listarPedidos() {
        LinkedList<Pedido> pedidos = controllerPedido.mostrarPedidos();
        StringBuilder sb = new StringBuilder("--- LISTA DE PEDIDOS ---\n");
        for (Pedido pedido : pedidos) {
            sb.append(pedido).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }


    public static void mostrarVista() {
        JFrame frame = new JFrame("Lista de Pedidos");
        ControllerPedido controllerPedido = new ControllerPedido();
        VistaListaPedidos vista = new VistaListaPedidos(controllerPedido);
        frame.setContentPane(vista);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}