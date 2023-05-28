
import java.util.HashMap;
import java.util.Map;

public class ListaDePrecios {
    private Map<Integer, Float> precios;
    private String fechaInicio;
    private String fechaFin;

    public ListaDePrecios(String fechaInicio, String fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precios = new HashMap<>();
    }

    public void asociarPrecio(int codigoProducto, float precio) {
        precios.put(codigoProducto, precio);
    }

    public float obtenerPrecio(int codigoProducto) {
        return precios.getOrDefault(codigoProducto, 0.0f);
    }

    public String obtenerFechaInicio() {
        return fechaInicio;
    }

    public String obtenerFechaFin() {
        return fechaFin;
    }

    public void listarPrecios() {
        for (Map.Entry<Integer, Float> entry : precios.entrySet()) {
            int codigoProducto = entry.getKey();
            float precio = entry.getValue();
            System.out.println("Código de producto: " + codigoProducto + ", Precio: " + precio);
        }
    }


}





