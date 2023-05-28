import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FacturaPantalla extends JFrame{
    private JTable tblFactura;
    private JPanel jpFactura;
    private JLabel lblcuitTienda;
    private JLabel lblNombreTienda;
    private JLabel lblTelTienda;
    private JLabel lblPtoVta;
    private JLabel lblNumFac;
    private JLabel lblFecha;
    private JLabel lblTotal;
    private JLabel lblCodCliente;
    private JLabel lblDniCliente;
    private JLabel lblNomCli;
    private JLabel lblFormaPago;

    public FacturaPantalla(Factura factura,ArrayList<Producto> producto,Tienda tienda,Cliente cliente){
        setContentPane(jpFactura);
        lblcuitTienda.setText(tienda.getCuit());
        lblNombreTienda.setText(tienda.getNombre());
        lblTelTienda.setText(tienda.getTelefono());
        lblPtoVta.setText(String.valueOf(factura.obtenerPuntoVenta()));
        lblNumFac.setText(String.valueOf(factura.obtenerNumeroFactura()));
        lblFecha.setText(factura.obtenerFecha());
        lblTotal.setText(String.valueOf(factura.obtenerTotal()));
        lblNomCli.setText(cliente.getNombre());
        lblCodCliente.setText(String.valueOf(cliente.getCode()));
        lblDniCliente.setText(String.valueOf(cliente.getDni()));


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Detalle");
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        model.addColumn("Subtotal");


        tblFactura.setModel(model);
        tblFactura.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false;
            }
        });
       factura.obtenerDetalle().forEach(elemento ->

        //int codigoPro=elemento.getCodigoProducto();
        //String nombre = producto.get(codigo).getNombre();
        //String descripcion = producto.get(codigo).getDescripcion();
        //int cantidad = Integer.parseInt(textField3.getText());
        //float precio = lista.obtenerPrecio(codigo);
        model.addRow(new Object[]{elemento.getCodigoProducto(),producto.get(elemento.getCodigoProducto()).getNombre(),
                producto.get(elemento.getCodigoProducto()).getDescripcion(), elemento.getCantidad(), elemento.getPrecio(),
                elemento.getCantidad() * elemento.getPrecio()})
       );

    }




}

