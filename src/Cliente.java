public class Cliente extends Persona {

    private int code;
    private int dni;
    public void setCode(int code) {
        this.code = code;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCode() {
        return code;
    }

    public int getDni() {
        return dni;
    }
    public Cliente(int code,int dni) {
        this.code=code;
        this.dni = dni;

    }

}