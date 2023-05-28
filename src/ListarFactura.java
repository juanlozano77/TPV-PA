import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ListarFactura extends JFrame {
    private JPanel jpanel4;
    private JButton button1;
    private JTextField textField2;
    private JTable table1;
    private JSpinner spinner1;

    public ListarFactura(ArrayList<Factura> factura,ArrayList<Cliente> clientes){
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinner1.setModel(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner1, "dd/MM/yyy");
        spinner1.setEditor(dateEditor);
        spinner1.setValue(new Date());
        System.out.println(new Date());
        setContentPane(jpanel4);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fecha");
        model.addColumn("Punto de Venta");
        model.addColumn("Numero Factura");
        model.addColumn("Cod. Cliente");
        model.addColumn("Numero Cliente");
        model.addColumn("Dni Cliente");
        model.addColumn("Total");
        table1.setModel(model);
        table1.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false;
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factura.forEach(elemento -> {
                    String fecha=elemento.obtenerFecha();
                    int puntoVenta=elemento.obtenerPuntoVenta();
                    int NumeroFactura= elemento.obtenerNumeroFactura();
                    int codCliente=elemento.getCodCliente();
                    String nombreCliente=clientes.get(codCliente).getNombre();
                    int dniCliente=clientes.get(codCliente).getDni();
                    float Total=elemento.obtenerTotal();
                    model.addRow(new Object[]{fecha,puntoVenta,NumeroFactura,codCliente,nombreCliente,dniCliente,Total});
                });


            }
        });
    }
}
