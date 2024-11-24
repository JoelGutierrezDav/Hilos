package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JButton clienteButton;
    private JButton facturaButton;
    private JButton inventarioButton;
    private JButton pedidoButton;
    private JButton productoButton;

    public MenuFrame() {
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 0, 400, 300);
        add(desktopPane);

        clienteButton = new JButton("Gestión de Clientes");
        clienteButton.setBounds(10, 10, 180, 30);
        add(clienteButton);

        facturaButton = new JButton("Gestión de Facturas");
        facturaButton.setBounds(210, 10, 180, 30);
        add(facturaButton);

        inventarioButton = new JButton("Gestión de Inventario");
        inventarioButton.setBounds(10, 50, 180, 30);
        add(inventarioButton);

        pedidoButton = new JButton("Gestión de Pedidos");
        pedidoButton.setBounds(210, 50, 180, 30);
        add(pedidoButton);

        productoButton = new JButton("Gestión de Productos");
        productoButton.setBounds(10, 90, 180, 30);
        add(productoButton);

        // Action Listeners for buttons
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteFrame clienteFrame = new ClienteFrame();
                desktopPane.add(clienteFrame);
                clienteFrame.setVisible(true);
            }
        });

        facturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacturaFrame facturaFrame = new FacturaFrame();
                desktopPane.add(facturaFrame);
                facturaFrame.setVisible(true);
            }
        });

        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventarioFrame inventarioFrame = new InventarioFrame();
                desktopPane.add(inventarioFrame);
                inventarioFrame.setVisible(true);
            }
        });

        pedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoFrame pedidoFrame = new PedidoFrame();
                desktopPane.add(pedidoFrame);
                pedidoFrame.setVisible(true);
            }
        });

        productoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoFrame productoFrame = new ProductoFrame();
                desktopPane.add(productoFrame);
                productoFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MenuFrame mainFrame = new MenuFrame();
        mainFrame.setVisible(true);
    }
}