import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button2;

    public Login(Empleado empleado, ArrayList<Cliente> clientes, ArrayList<Producto> producto, ListaDePrecios lista, ArrayList<Factura>factura,
                 Inventario inventario,ArrayList<FormaDePago> formaDePago,Tienda tienda){
        super("TPV-Programacion Avanzada-Login");
        setContentPane(panel1);



        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(empleado.getUsuario())&&
                passwordField1.getText().equals(empleado.getPass())){
                    JOptionPane.showMessageDialog(null,
                            "Credenciales correctas");
                    Login.super.setVisible(false);
                    PanelControl panel= new PanelControl(clientes, producto, lista,factura,inventario,formaDePago,tienda);
                    panel.setSize(800,800);
                    panel.setVisible(true);

                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Credenciales incorrectas. Sale del sistema");

                }
            }
        });
    }
}
