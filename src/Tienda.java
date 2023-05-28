public class Tienda extends Persona {
    private long cuit;
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public long getCuit() {
        return cuit;
    }
    public Tienda(long cuit) {
        this.cuit = cuit;

    }

}
