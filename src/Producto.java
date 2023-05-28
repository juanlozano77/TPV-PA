public class Producto {
    private int id;
    private String nombre;
    private String descripcion;

    public String getNombre(){
        return nombre;
    }
    public int getCode(){
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

    // Constructor de la clase Producto
    public Producto(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }



    public void imprimirInformacion() {
        System.out.println("Información del producto:");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println();
    }
}