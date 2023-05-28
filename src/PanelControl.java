import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
public class PanelControl extends JFrame{
    private JButton clientesButton;
    private JButton reportesButton;
    private JPanel jpanel3;
    private JTabbedPane tabbedPane1;
    private JPanel jpanelFactura;
    private JSpinner spinner1;
    private JComboBox comboBox2;
    private JComboBox comboBox1;
    private JTable table1;
    private JTextField textField3;
    private JButton agregarButton;
    private JLabel cantTotal;
    private JButton editarButton;
    private JButton aceptarButton;
    private JTextField txtDni;
    private JTextField txtTel;
    private JTextField txtNombre;
    private JTable tableCli;
    private JButton cliAgregarButton1;
    private JButton cliEditarButton1;
    private JTextField txtDire;
    private JTextField txtDespro;
    private JTable tablePro;
    private JTextField txtNomPro;
    private JTextField txtCanPro;
    private JTextField txtPrePro;
    private JButton agregarProButton1;
    private JButton editarButton1;

    public PanelControl(ArrayList<Cliente> clientes, ArrayList<Producto> producto, ListaDePrecios lista, ArrayList<Factura> factura,
                        Inventario inventario) {
        super("TPV-Grupo S");
        setContentPane(jpanel3);
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinner1.setModel(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner1, "dd/MM/yyyy");
        spinner1.setEditor(dateEditor);
        spinner1.setValue(new Date());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Detalle");
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        model.addColumn("Subtotal");


        table1.setModel(model);
        table1.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false;
            }
        });

        DefaultTableModel modelCli = new DefaultTableModel();
        modelCli.addColumn("Codigo");
        modelCli.addColumn("Dni");
        modelCli.addColumn("Nombre");
        modelCli.addColumn("Telefono");
        modelCli.addColumn("Direccion");

        tableCli.setModel(modelCli);
        tableCli.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false;
            }
        });
        DefaultTableModel modelPro = new DefaultTableModel();
        modelPro.addColumn("Codigo");
        modelPro.addColumn("Nombre");
        modelPro.addColumn("Descripcion");
        modelPro.addColumn("Cantidad Actual");
        modelPro.addColumn("Precio Actual");

        tablePro.setModel(modelPro);
        tablePro.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false;
            }
        });





        agregarButton.addActionListener(new ActionListener() {
            private boolean existeCodigo(int valor1) {
                for (int row = 0; row < model.getRowCount(); row++) {
                    int value1 = Integer.parseInt(model.getValueAt(row, 0).toString());
                    if (value1 == valor1) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = comboBox1.getSelectedIndex();
                if (existeCodigo(codigo)) {
                    JOptionPane.showMessageDialog(null,
                            "El articulo ya se ingresó");

                    return;
                }
                String nombre = producto.get(codigo).getNombre();
                String descripcion = producto.get(codigo).getDescripcion();
                int cantidad = Integer.parseInt(textField3.getText());

                if (inventario.obtenerCantidad(codigo) < cantidad) {
                    JOptionPane.showMessageDialog(null,
                            "La cantidad supera al inventario actual");

                    return;
                }
                float precio = lista.obtenerPrecio(codigo);
                model.addRow(new Object[]{codigo, nombre, descripcion, cantidad, precio, cantidad * precio});
                actualizarTotal(model);


            }

        });

        reportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reportes reporte = new Reportes(clientes, producto, lista, factura, inventario);
                reporte.setSize(300, 300);
                reporte.setVisible(true);
            }
        });
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!table1.getSelectionModel().isSelectionEmpty()) {
                        int fila = table1.getSelectedRow();
                        int valor = Integer.parseInt(model.getValueAt(fila, 0).toString());
                        comboBox1.setSelectedIndex(valor);
                        String texto = model.getValueAt(fila, 3).toString();
                        textField3.setText(texto);
                    }
                }
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override


            public void actionPerformed(ActionEvent e) {
                table1.clearSelection();
                int indice = factura.size();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fecha = dateFormat.format(spinner1.getValue());
                factura.add(new Factura(fecha, comboBox2.getSelectedIndex(), 1, indice + 1));
                for (int row = 0; row < model.getRowCount(); row++) {
                    int codigo = Integer.parseInt(model.getValueAt(row, 0).toString());
                    float precio = Float.parseFloat(model.getValueAt(row, 4).toString());
                    int cantidad = Integer.parseInt(model.getValueAt(row, 3).toString());

                    factura.get(indice).agregarDetalle(codigo, precio, cantidad);
                    factura.get(indice).mostrarDetalle();
                    inventario.actualizarProducto(codigo, inventario.obtenerCantidad(codigo) - cantidad);


                }
                model.setRowCount(0);
                JOptionPane.showMessageDialog(null,
                        "Factura Creada");

            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = table1.getSelectedRow();
                int codigo = Integer.parseInt(model.getValueAt(fila, 0).toString());
                String nombre = producto.get(codigo).getNombre();
                String descripcion = producto.get(codigo).getDescripcion();
                int cantidad = Integer.parseInt(textField3.getText());

                if (inventario.obtenerCantidad(codigo) < cantidad) {
                    JOptionPane.showMessageDialog(null,
                            "La cantidad supera al inventario actual");

                    return;
                }
                float precio = lista.obtenerPrecio(codigo);

                model.setValueAt(cantidad, fila, 3);
                model.setValueAt(cantidad * precio, fila, 5);
                actualizarTotal(model);


            }
        });
        tableCli.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!tableCli.getSelectionModel().isSelectionEmpty()) {

                        int fila = tableCli.getSelectedRow();
                        txtDni.setText(modelCli.getValueAt(fila,1).toString());
                        txtNombre.setText(modelCli.getValueAt(fila,2).toString());
                        txtTel.setText(modelCli.getValueAt(fila,3).toString());
                        txtDire.setText(modelCli.getValueAt(fila,4).toString());

                    }
                }
            }
        });
        cliAgregarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ultimo=clientes.size();
                clientes.add(new Cliente(ultimo,Integer.parseInt(txtDni.getText())));
                clientes.get(ultimo).Persona(txtNombre.getText(),txtTel.getText(),txtDire.getText());
                JOptionPane.showMessageDialog(null,
                        "Cliente Agregado");
                modelCli.addRow(new Object[]{ultimo,txtDni.getText(),txtNombre.getText(),txtTel.getText(),txtDire.getText() });


            }
        });

        tablePro.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!tablePro.getSelectionModel().isSelectionEmpty()) {
                        int fila = tablePro.getSelectedRow();
                        txtNomPro.setText(modelPro.getValueAt(fila,1).toString());
                        txtDespro.setText(modelPro.getValueAt(fila,2).toString());
                        txtCanPro.setText(modelPro.getValueAt(fila,3).toString());
                        txtPrePro.setText(modelPro.getValueAt(fila,4).toString());

                    }
                }
            }
        });
        tabbedPane1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                comboBox2.removeAllItems();
                for (Cliente elemento : clientes) {
                    comboBox2.addItem(elemento.getDni()+"-"+elemento.getNombre());
                }
                comboBox1.removeAllItems();

                for (Producto elemento : producto) {
                    comboBox1.addItem(elemento.getCode()+"-"+elemento.getNombre());
                }

            }
        });
        agregarProButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo=producto.size();
                producto.add(new Producto(codigo, txtNomPro.getText(),txtDespro.getText()));
                inventario.actualizarProducto(codigo,Integer.parseInt(txtCanPro.getText()));
                lista.asociarPrecio(codigo,Float.parseFloat(txtPrePro.getText()));
                JOptionPane.showMessageDialog(null,
                        "Producto Agregado");
            }
        });
        tabbedPane1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                modelCli.setRowCount(0);
                clientes.forEach((elemento ->
                        modelCli.addRow(new Object[]{elemento.getCode(),elemento.getDni(),elemento.getNombre(),elemento.getTelefono(),elemento.getDireccion() })));

            }
        });
        cliEditarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tableCli.getSelectedRow();
                int codigo = Integer.parseInt(modelCli.getValueAt(fila, 0).toString());
                System.out.println(codigo);
                modelCli.setValueAt(txtDni.getText(),fila,1);
                modelCli.setValueAt(txtNombre.getText(),fila,2);
                modelCli.setValueAt(txtTel.getText(),fila,3);
                modelCli.setValueAt(txtDire.getText(),fila,4);
                clientes.get(codigo).setDni(Integer.parseInt(txtDni.getText()));
                clientes.get(codigo).Persona(txtNombre.getText(),txtTel.getText(),txtDire.getText());



            }
        });
        tabbedPane1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                modelPro.setRowCount(0);
                producto.forEach((elemento ->
                        modelPro.addRow(new Object[]{elemento.getCode(),elemento.getNombre(),elemento.getDescripcion(),
                        inventario.obtenerCantidad(elemento.getCode()) ,lista.obtenerPrecio(elemento.getCode())
                        })));};});


        editarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablePro.getSelectedRow();
                int codigo = Integer.parseInt(modelPro.getValueAt(fila, 0).toString());
                System.out.println(codigo);
                modelPro.setValueAt(txtNomPro.getText(),fila,1);
                modelPro.setValueAt(txtDespro.getText(),fila,2);
                modelPro.setValueAt(txtCanPro.getText(),fila,3);
                modelPro.setValueAt(txtPrePro.getText(),fila,4);
                producto.get(codigo).setNombre(txtNomPro.getText());
                producto.get(codigo).setDescripcion(txtDespro.getText());
                lista.asociarPrecio(codigo,Float.parseFloat(txtPrePro.getText()));
                inventario.actualizarProducto(codigo,Integer.parseInt(txtCanPro.getText()));

            }
        });
    };

        public void actualizarTotal(DefaultTableModel model) {
            float total = 0;
            for (int row = 0; row < model.getRowCount(); row++) {
                float precio = Float.parseFloat(model.getValueAt(row, 4).toString());
                int cantidad = Integer.parseInt(model.getValueAt(row, 3).toString());
                total = total + (precio * cantidad);
            }
            cantTotal.setText(Float.toString(total));
        }
}
