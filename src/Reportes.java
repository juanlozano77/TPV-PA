import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Reportes extends JFrame {
    private JPanel jpanel4;
    private JButton reimprimirFacturaButton;
    private JButton listarFacturasButton;
    private JButton imprimirInventarioButton;

    public Reportes(ArrayList<Cliente> clientes, ArrayList<Producto> producto, ListaDePrecios lista, ArrayList<Factura> factura,
                    Inventario inventario,ArrayList<FormaDePago> formaDePago) {
        super("Reportes");
        setContentPane(jpanel4);
        listarFacturasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarFactura listarFactura= new ListarFactura(factura,clientes,formaDePago);
                listarFactura.setSize(800,300);
                listarFactura.setVisible(true);
            }
        });
    }
}
