public class Empleado extends Persona {
    private String usuario;
    private String pass;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }
    public Empleado(String usuario, String pass) {
        this.usuario= usuario;
        this.pass = pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}