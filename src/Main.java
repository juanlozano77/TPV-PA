import javax.swing.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        empleados.add(new Empleado("admin", "1234"));
        empleados.get(0).Persona("Juan Perez", "4298-0549", "Saavedra 459");
        empleados.get(0).imprimirPersona();

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(0, 28555315));
        clientes.get(0).Persona("Maria", "1567025280", "Segui 848");
        clientes.add(new Cliente(1, 34345412));
        clientes.get(1).Persona("Ana", "1530298080", "Mitre 848");

        Tienda lomas = new Tienda("3054949396");
        lomas.Persona("The Old Crew", "42928648", "oliden 846");
        lomas.imprimirPersona();
        ArrayList<FormaDePago> formaDePago = new ArrayList<FormaDePago>();
        formaDePago.add(new FormaDePago(0, "efectivo"));
        formaDePago.add(new FormaDePago(1, "debito"));
        formaDePago.add(new FormaDePago(2, "credito"));

        ArrayList<Producto> productos = new ArrayList<Producto>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/productos.csv"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                int codigo = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String descripcion = datos[2];

                productos.add(new Producto(codigo, nombre, descripcion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        ListaDePrecios listaPrecios23 = new ListaDePrecios("2023/01/01", "2023/12731");

        listaPrecios23.asociarPrecio(0, 10);

        try (BufferedReader br = new BufferedReader(new FileReader("src/precios.csv"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                int codigo = Integer.parseInt(datos[0]);
                float precio= Float.parseFloat(datos[1]);
                listaPrecios23.asociarPrecio(codigo,precio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listaPrecios23.listarPrecios();
        Inventario inventario = new Inventario();
        try (BufferedReader br = new BufferedReader(new FileReader("src/inventario.csv"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                int codigo = Integer.parseInt(datos[0]);
                int cantidad= Integer.parseInt(datos[1]);
                inventario.actualizarProducto(codigo,cantidad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        inventario.actualizarProducto(0, 10);
        inventario.actualizarProducto(1, 15);
        inventario.actualizarProducto(2, 30);
        inventario.imprimirInventario();

        productos.get(0).imprimirInformacion();
        productos.get(1).imprimirInformacion();
        productos.get(2).imprimirInformacion();

        ArrayList<Factura> facturas = new ArrayList<Factura>();

        facturas.add(new Factura("2023/04/03", 0, 1, 1,0));
        facturas.get(0).agregarDetalle(1, 15.5f, 2);
        facturas.get(0).agregarDetalle(2, 15.5f, 2);


        facturas.add(new Factura("2023/01/07", 0, 1, 2,1));
        facturas.get(1).agregarDetalle(3, 8.4f, 3);
        facturas.get(1).agregarDetalle(4, 8.4f, 4);
        facturas.get(1).mostrarDetalle();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login(empleados.get(0),clientes, productos, listaPrecios23,facturas,inventario,formaDePago,lomas);
                login.setSize(300,300);
                login.setVisible(true);





            }
        });






    }
}
