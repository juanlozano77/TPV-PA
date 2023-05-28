import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Integer, Integer> cantidades;

    public Inventario() {

        this.cantidades = new HashMap<>();
    }

    public void actualizarProducto(int codigoProducto, int cantidad) {
        cantidades.put(codigoProducto,cantidad);
    }

    public int obtenerCantidad(int codigoProducto) {
        return cantidades.getOrDefault(codigoProducto,0);
    }
    public void imprimirInventario() {
        for (Map.Entry<Integer, Integer> entry : cantidades.entrySet()) {
            int codigoProducto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("Código de producto: " + codigoProducto + ", Cantidad: " + cantidad);
        }
    }
}