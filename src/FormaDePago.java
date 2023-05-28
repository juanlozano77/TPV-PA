public class FormaDePago {
    private int id;
    private String nombre;

    public int obtenerId() {
        return id;
    }

    public FormaDePago(int id, String nombre) {
        this.id=id;
        this.nombre = nombre;

    }


    public String obtenerNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}