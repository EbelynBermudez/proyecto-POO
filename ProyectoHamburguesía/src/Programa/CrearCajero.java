package Programa;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Logica.Cajero;

public class CrearCajero {
    static ArrayList<Cajero> listaCajeros = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);
    private static final String archivoCajeros = "cajeros.txt";

    public static void guardarCajeros() {
        try {
            System.out.println("Ingrese el nombre del cajero:");
            String nombre = entrada.next();
            System.out.println("Ingrese el id del cajero:");
            int id = entrada.nextInt();
            Cajero cajero = new Cajero(nombre, id);
            listaCajeros.add(cajero);

            guardarCajerosEnArchivo(cajero);
            System.out.println("Cajero creado con éxito.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor numérico válido para el ID del cajero.");
            entrada.nextLine();  
        }
    }

    public static void mostrarCajeros() {
        cargarCajerosDesdeArchivo();

        for (Cajero cajero : listaCajeros) {
            System.out.println("Nombre: " + cajero.getNombre());
            System.out.println("Id: " + cajero.getId());
        }
    }

    public static Cajero buscarCajero(int idCajero) {
        cargarCajerosDesdeArchivo();

        for (Cajero cajero : listaCajeros) {
            if (cajero.getId() == idCajero) {
                return cajero;
            }
        }

        return null;
    }
    private static void guardarCajerosEnArchivo(Cajero cajero) {
        try (FileWriter writer = new FileWriter(archivoCajeros, true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("Nombre del Cajero: " + cajero.getNombre());
            bw.newLine();
            bw.write("ID del Cajero: " + cajero.getId());
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarCajerosDesdeArchivo() {
        try (FileReader reader = new FileReader(archivoCajeros);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre del Cajero: ")) {
                    String nombre = line.substring("Nombre del Cajero: ".length());
                    line = br.readLine();  
                    if (line.startsWith("ID del Cajero: ")) {
                        int id = Integer.parseInt(line.substring("ID del Cajero: ".length()));
                        Cajero cajero = new Cajero(nombre, id);
                        listaCajeros.add(cajero);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

