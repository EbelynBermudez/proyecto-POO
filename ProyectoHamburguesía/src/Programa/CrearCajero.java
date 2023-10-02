package Programa;

import java.util.ArrayList;
import java.util.Scanner;

import Logica.Cajero;

public class CrearCajero {
    private static ArrayList<Cajero> listaCajeros = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);

    public static void guardarCajeros() {
        System.out.println("Ingrese el nombre del cajero:");
        String nombre = entrada.next();
        System.out.println("Ingrese el id del cajero:");
        int id = entrada.nextInt();
        Cajero cajero = new Cajero(nombre, id);
        listaCajeros.add(cajero);
    }

    public static void mostrarCajeros() {
        for (Cajero cajero : listaCajeros) {
            System.out.println("Nombre: " + cajero.getNombre());
            System.out.println("Id: " + cajero.getId());
        }
    }
}
