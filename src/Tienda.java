public class Tienda extends Persona {
    private String cuit;
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCuit() {
        return cuit;
    }
    public Tienda(String cuit) {
        this.cuit = cuit;

    }

}
