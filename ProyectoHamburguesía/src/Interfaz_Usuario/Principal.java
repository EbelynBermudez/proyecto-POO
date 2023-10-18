package Interfaz_Usuario;

import java.util.Scanner;
import Programa.CrearCajero;
import Programa.CrearCombo;
import Programa.CrearFactura;
import Programa.CrearProducto;

public class Principal {
	static Scanner entrada = new Scanner ( System.in);
    public static void main(String[] args) {

  
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
            	CrearFactura.crearFactura();
            } else if (x == 8) {
            	CrearFactura.imprimirFacturaa();
            } else if (x == 9) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Número no válido...");
            }
        } while (x != 9);
    }
}
    