package Programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Logica.Productos;

public class CrearProducto {
    public static ArrayList<Productos> listaProductos = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);

    public static void guardarProducto() {
        System.out.println("Ingrese el nombre del producto:");
        String nombre = entrada.next();
        System.out.println("Ingrese el tipo de " + nombre);
        String tipoProducto = entrada.next();
        System.out.println("Ingrese el código de " + nombre + " " + tipoProducto);
        int codigo = entrada.nextInt();
        System.out.println("Ingrese el precio del producto:");
        float precio = entrada.nextFloat();
        Productos producto = new Productos(nombre, precio, tipoProducto, codigo);
        System.out.println("Ingrese los ingredientes del producto (separados por comas):");
        entrada.nextLine();
        String ingredientes = entrada.nextLine();
        String[] ingredientesArray = ingredientes.split(",");
        for (String ingrediente : ingredientesArray) {
            producto.agregarIngrediente(ingrediente.trim());
        }
        listaProductos.add(producto);

        guardarProductosEnArchivo(producto);
    }

    public static void mostrarProductos() {
        for (Productos producto : listaProductos) {
            System.out.println("Nombre: " + producto.getNombre() + " " + producto.getTipoProducto());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Ingredientes: " + producto.getIngredientes());
        }
    }

    public static void buscarProducto() {
        System.out.println("Ingrese el nombre del producto");
        String nombreBuscado = entrada.next();
        boolean encontrado = false;

        for (Productos producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Producto encontrado:");
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Tipo: " + producto.getTipoProducto());
                System.out.println("Código: " + producto.getCodigo());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    public static ArrayList<String> obtenerIngredientesPorCodigo(int codigo) {
        ArrayList<String> ingredientesEncontrados = new ArrayList<>();

        for (Productos producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                ingredientesEncontrados.addAll(producto.getIngredientes());
            }
        }

        return ingredientesEncontrados;
    }

    private static void guardarProductosEnArchivo(Productos producto) {
        try (FileWriter writer = new FileWriter("productos.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("Nombre: " + producto.getNombre() + " " + producto.getTipoProducto());
            bw.newLine();
            bw.write("Precio: " + producto.getPrecio());
            bw.newLine();
            bw.write("Código: " + producto.getCodigo());
            bw.newLine();
            bw.write("Ingredientes: " + String.join(",", producto.getIngredientes()));
            bw.newLine();
            bw.newLine();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void leerArchivos() {
        try (FileReader reader = new FileReader("productos.txt");
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre: ")) {
                    String nombreTipo = line.substring("Nombre: ".length());
                    String[] nombreTipoArray = nombreTipo.split(" ");
                    String nombre = nombreTipoArray[0];
                    String tipoProducto = nombreTipoArray[1];

                    float precio = Float.parseFloat(br.readLine().substring("Precio: ".length()));
                    int codigo = Integer.parseInt(br.readLine().substring("Código: ".length()));
                    String ingredientesLine = br.readLine().substring("Ingredientes: ".length());
                    String[] ingredientesArray = ingredientesLine.split(",");

                    Productos producto = new Productos(nombre, precio, tipoProducto, codigo);
                    for (String ingrediente : ingredientesArray) {
                        producto.agregarIngrediente(ingrediente.trim());
                    }

                    listaProductos.add(producto);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Productos buscarProductoPorCodigo(int codigo) {
        for (Productos producto : CrearProducto.listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public static Productos buscarProductoPorNombreYTipo(String nombre, String tipo) {
        for (Productos producto : CrearProducto.listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre) && producto.getTipoProducto().equalsIgnoreCase(tipo)) {
                return producto;
            }
        }
        return null;
    }


}

