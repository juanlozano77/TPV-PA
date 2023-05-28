
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DetalleFactura {
    private int codigoProducto;
    private float precio;
    private int cantidad;

    public DetalleFactura(int codigoProducto, float precio, int cantidad) {
        this.codigoProducto = codigoProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}