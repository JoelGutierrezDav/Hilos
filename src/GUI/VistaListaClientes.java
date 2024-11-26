package GUI;

import javax.swing.*;
import DLL.ControllerCliente;
import BLL.Cliente;
import java.util.LinkedList;

public class VistaListaClientes extends JPanel {
    private ControllerCliente controllerCliente;

    public VistaListaClientes(ControllerCliente controllerCliente) {
        this.controllerCliente = controllerCliente;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        JButton btnListar = new JButton("Listar Clientes");
        btnListar.setBounds(10, 20, 255, 25);
        btnListar.addActionListener(e -> listarClientes());
        add(btnListar);
    }

    private void listarClientes() {
        LinkedList<Cliente> clientes = controllerCliente.mostrarClientes();
        StringBuilder sb = new StringBuilder("--- LISTA DE CLIENTES ---\n");
        for (Cliente cliente : clientes) {
            sb.append(cliente).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}