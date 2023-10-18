package Programa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Logica.Combos;
import Logica.Factura;
import Logica.Productos;

public class CrearCombo {
    public static ArrayList<Combos> listaCombos = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);

    public static void guardarCombo() {
    	System.out.println("Ingrese el código del combo:");
        int codigoCombo = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Ingrese el nombre del combo:");
        String nombre = entrada.nextLine();
        Combos combo = new Combos(codigoCombo, nombre);

        boolean agregarOtroProducto = true;
        double precioTotalCombo = 0.0;

        while (agregarOtroProducto) {
            System.out.println("Ingrese el código del producto que desea agregar al combo (0 para finalizar):");
            int codigo = entrada.nextInt();

            if (codigo == 0) {
                agregarOtroProducto = false;
                continue;
            }

            Productos productoEncontrado = CrearProducto.buscarProductoPorCodigo(codigo);

            if (productoEncontrado != null) {
                combo.agregarProductoAlCombo(productoEncontrado);
                precioTotalCombo += productoEncontrado.getPrecio();
                System.out.println("Producto agregado al combo.");

                System.out.println("¿Desea agregar otro producto al combo? (S/N)");
                String respuesta = entrada.next();
                if (!respuesta.equalsIgnoreCase("S")) {
                    agregarOtroProducto = false;
                }
            } else {
                System.out.println("Producto no encontrado. Por favor, ingrese un código válido o 0 para finalizar.");
            }
        }

        double descuento = precioTotalCombo * 0.20;
        double precioFinalCombo = precioTotalCombo - descuento;

        combo.setPrecio(precioFinalCombo);
        listaCombos.add(combo);
        System.out.println("Combo creado con éxito.");

        guardarComboEnArchivo(combo);
    }


  
    private static void guardarComboEnArchivo(Combos combo) {
        try (FileWriter writer = new FileWriter("combos.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("Código del Combo: " + combo.getCodigoc()); 
            bw.newLine();
            bw.write("Nombre del Combo: " + combo.getNombre());
            bw.newLine();
            bw.write("Precio del Combo: " + combo.getPrecio());
            bw.newLine();
            bw.write("Productos en el Combo:");
            bw.newLine();
            for (Productos producto : combo.getProductosEnCombo()) {
                bw.write(" - Nombre: " + producto.getNombre() + " Tipo: " + producto.getTipoProducto());
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Combos> leerCombosDesdeArchivo(String nombreArchivo) {
        ArrayList<Combos> combosLeidos = new ArrayList<>();

        try (FileReader reader = new FileReader(nombreArchivo);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            Combos combo = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Código del Combo: ")) {
                    int codigo = Integer.parseInt(line.substring("Código del Combo: ".length()));
                    combo = new Combos(codigo, ""); 
                } else if (combo != null) { 
                    if (line.startsWith("Nombre del Combo: ")) {
                        String nombre = line.substring("Nombre del Combo: ".length());
                        combo.setNombre(nombre); 
                    } else if (line.startsWith("Precio del Combo: ")) {
                        double precio = Double.parseDouble(line.substring("Precio del Combo: ".length()));
                        combo.setPrecio(precio);
                    } else if (line.startsWith(" - Nombre: ")) {
                        String[] partes = line.split(" Tipo: ");
                        String nombreProducto = partes[0].substring(" - Nombre: ".length());
                        String tipoProducto = partes[1];
                        Productos producto = CrearProducto.buscarProductoPorNombreYTipo(nombreProducto, tipoProducto);
                        if (producto != null) {
                            combo.agregarProductoAlCombo(producto);
                        }
                    } else if (line.isEmpty()) {
                        combosLeidos.add(combo);
                        combo = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return combosLeidos;
    }


    public static void mostrarCombosDesdeArchivo(String nombreArchivo) {
        ArrayList<Combos> combosLeidos = leerCombosDesdeArchivo(nombreArchivo);

        if (combosLeidos.isEmpty()) {
            System.out.println("No hay combos disponibles en el archivo: " + nombreArchivo);
        } else {
            System.out.println("Combos disponibles en el archivo " + nombreArchivo + ":");
            for (Combos combo : combosLeidos) {
                System.out.println("Nombre del Combo: " + combo.getNombre());
                System.out.println("Precio del Combo: " + combo.getPrecio());
                System.out.println("Productos en el Combo:");
                for (Productos producto : combo.getProductosEnCombo()) {
                    System.out.println(" - Nombre: " + producto.getNombre() + " Tipo: " + producto.getTipoProducto());
                }
                System.out.println();
            }
        }
    }
    public static Combos buscarComboDesdeArchivo(String nombreArchivo, int codigoCombo) {
        ArrayList<Combos> combosLeidos = leerCombosDesdeArchivo(nombreArchivo);

        for (Combos combo : combosLeidos) {
            if (combo.getCodigoc() == codigoCombo) {
                return combo;
            }
        }

        return null; 
    }
    public static void agregarCombo(Factura factura, int codigoCombo) {
        Combos combo = CrearCombo.buscarComboDesdeArchivo("combos.txt", codigoCombo);

        if (combo != null) {
            factura.agregarCombo(combo);
            System.out.println("Combo agregado a la factura.");
        } else {
            System.out.println("Combo no encontrado o no válido.");
        }
    }


}

