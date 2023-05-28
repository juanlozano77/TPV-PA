import java.util.ArrayList;
import java.util.List;
class Factura {
    private String fecha;
    private int codCliente;
    private int puntoVenta;
    private int nroFactura;
    private float total;
    private List<DetalleFactura> detalle;

    public Factura(String fecha, int codCliente, int puntoVenta, int nroFactura) {
        this.fecha = fecha;
        this.codCliente = codCliente;
        this.puntoVenta = puntoVenta;
        this.nroFactura = nroFactura;
        this.detalle = new ArrayList<>();
    }

    public String obtenerFecha() {
        return fecha;
    }
    public int getCodCliente(){return codCliente;}

    public int obtenerPuntoVenta() {
        return puntoVenta;
    }

    public int obtenerNumeroFactura() {
        return nroFactura;
    }

    public void agregarDetalle(int codigoProducto, float precio, int cantidad) {
        DetalleFactura detalleFactura = new DetalleFactura(codigoProducto, precio, cantidad);
        detalle.add(detalleFactura);
    }

    public void mostrarDetalle() {
        System.out.println("Detalle de la factura:");
        for (DetalleFactura detalleFactura : detalle) {
            System.out.println("Código de producto: " + detalleFactura.getCodigoProducto());
            System.out.println("Precio: " + detalleFactura.getPrecio());
            System.out.println("Cantidad: " + detalleFactura.getCantidad());
            System.out.println("---------------------------");
        }
    }
    public float obtenerTotal(){
        float total=0;
        for (DetalleFactura detalleFactura : detalle) {
         total=total+ detalleFactura.getPrecio()*detalleFactura.getCantidad();
        }
        return total;
    }
}