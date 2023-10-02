package Interfaz_Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Logica.Combos;
import Logica.FacturaCombo;
import Programa.CrearCajero;
import Programa.CrearCombo;
import Programa.CrearFactura;
import Programa.CrearProducto;
import Programa.VentaCombo;

public class Principal {
    private static Scanner entrada = new Scanner(System.in);
    private static List<Combos> listaCombos = new ArrayList<>();
    private static List<FacturaCombo> listaFacturasCombo = new ArrayList<>();

    private static void cargarCombosDesdeArchivo() {
        listaCombos = CrearCombo.leerCombosDesdeArchivo("combos.txt");
    }

    public static void main(String[] args) {
        CrearFactura.inicializar(CrearProducto.listaProductos);
        CrearProducto.leerArchivos();
        cargarCombosDesdeArchivo();
        int x;

        do {
            System.out.println("Dígite el número de la acción que desea realizar:\n" +
                    "1) Ingresar un Producto nuevo\n" +
                    "2) Mostrar lista de productos\n" +
                    "3) Agregar nuevo Cajero\n" +
                    "4) Mostrar lista de cajeros\n" +
                    "5) Agregar combos\n" +
                    "6) Mostrar combos\n" +
                    "7) Realizar venta\n" +
                    "8) Imprimir factura\n" +
                    "9) Salir");

            x = entrada.nextInt();

            if (x == 1) {
                CrearProducto.guardarProducto();
            } else if (x == 2) {
                System.out.println("Lista de productos: ");
                CrearProducto.mostrarProductos();
            } else if (x == 3) {
                CrearCajero.guardarCajeros();
            } else if (x == 4) {
                System.out.println("Lista de Cajeros");
                CrearCajero.mostrarCajeros();
            } else if (x == 5) {
                CrearCombo.guardarCombo();
            } else if (x == 6) {
                System.out.println("Lista de combos");
                CrearCombo.mostrarCombosDesdeArchivo("combos.txt");
            } else if (x == 7) {
                System.out.println("1) Combo\n" +
                        "2) Producto");
                int y = entrada.nextInt();
                if (y == 1) {
                    VentaCombo.realizarVentaCombo();
                } else if (y == 2) {
                    CrearFactura.agregarProducto();
                }
            } else if (x == 8) {
                System.out.println("1) Imprimir factura de combo\n" +
                        "2) Imprimir factura de producto");
                int X = entrada.nextInt();
                if (X == 1) {
                    System.out.println("Ingrese el número de factura de combo a imprimir:");
                    int numeroFacturaCombo = entrada.nextInt();

                    FacturaCombo facturaCombo = buscarFacturaC(numeroFacturaCombo, listaFacturasCombo);
                    if (facturaCombo != null) {
                        VentaCombo.imprimirFactura(facturaCombo);
                    } else {
                        System.out.println("Factura de combo no encontrada.");
                    }
                } else if (X == 2) {
                    CrearFactura.imprimirFactura();
                }
            } else if (x == 9) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Número no válido...");
            }
        } while (x != 9);
    }

    public static FacturaCombo buscarFacturaC(int numeroFacturaCombo, List<FacturaCombo> listaFacturasCombo) {
        for (FacturaCombo facturaCombo : listaFacturasCombo) {
            if (facturaCombo.getNumeroFactura() == numeroFacturaCombo) {
                return facturaCombo;
            }
        }
        return null;
    }
}
