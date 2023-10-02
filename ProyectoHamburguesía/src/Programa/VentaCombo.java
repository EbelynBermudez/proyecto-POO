package Programa;

import Logica.Combos;
import Logica.FacturaCombo;
import Logica.Productos;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VentaCombo {
    private static Scanner entrada = new Scanner(System.in);

    public static void realizarVentaCombo() {
        ArrayList<FacturaCombo> listaFacturas = new ArrayList<>();


        boolean agregarMasCombos = true;


        System.out.println("Ingrese el número de factura:");
        int numeroFactura = entrada.nextInt();

        System.out.println("Ingrese el ID del cajero:");
        int idCajero = entrada.nextInt();

        System.out.println("Ingrese la fecha de la compra (dd/MM/yyyy):");
        String fechaCompraStr = entrada.next();

        ArrayList<Combos> combosVendidos = new ArrayList<>();

        while (agregarMasCombos) {
            System.out.println("Ingrese el código del combo que desea agregar a la factura (0 para finalizar):");
            int codigoCombo = entrada.nextInt();

            if (codigoCombo == 0) {
                agregarMasCombos = false;
                continue;
            }

            Combos comboEncontrado = buscarComboPorCodigoEnArchivo(codigoCombo);

            if (comboEncontrado != null) {
                System.out.println("Ingrese la cantidad de este combo:");
                int cantidadCombo = entrada.nextInt();

                for (int i = 0; i < cantidadCombo; i++) {
                    combosVendidos.add(comboEncontrado);
                }

                System.out.println("Combo agregado a la factura.");

                System.out.println("¿Desea agregar otro combo a la factura? (S/N)");
                String respuesta = entrada.next();
                if (!respuesta.equalsIgnoreCase("S")) {
                    agregarMasCombos = false;
                }
            } else {
                System.out.println("Combo no encontrado. Por favor, ingrese un código válido o 0 para finalizar.");
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date fechaCompra = dateFormat.parse(fechaCompraStr);
            float total = calcularTotalFacturaCombo(combosVendidos);

            FacturaCombo facturaCombo = new FacturaCombo(idCajero, numeroFactura, fechaCompra, total, combosVendidos);
            listaFacturas.add(facturaCombo); 

            System.out.println("Venta de combo realizada con éxito.");
        } catch (ParseException e) {
            System.out.println("Fecha de compra inválida.");
        }
    }

    public static Combos buscarComboPorCodigoEnArchivo(int codigoCombo) {
        ArrayList<Combos> combosLeidos = new ArrayList<>();

        try (FileReader reader = new FileReader("combos.txt");
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

        for (Combos combo : combosLeidos) {
            if (combo.getCodigo() == codigoCombo) {
                return combo;
            }
        }
        return null;
    }
    public static float calcularTotalFacturaCombo(ArrayList<Combos> combosVendidos) {
        float total = 0;
        for (Combos combo : combosVendidos) {
            total += combo.getPrecio();
        }
        return total;
    }
    public static void imprimirFactura(FacturaCombo factura) {
        System.out.println("Factura Número: " + factura.getNumeroFactura());
        System.out.println("ID del Cajero: " + factura.getIdCajero());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Fecha de la Compra: " + dateFormat.format(factura.getFechaCompra()));
        
        System.out.println("Combos comprados:");

        for (Combos combo : factura.getCombosVendidos()) {
            System.out.println("  Nombre del Combo: " + combo.getNombre());
            System.out.println("  Precio del Combo: " + combo.getPrecio());
            System.out.println("  Productos en el Combo:");

            for (Productos producto : combo.getProductosEnCombo()) {
                System.out.println("    - Nombre: " + producto.getNombre());
                System.out.println("    - Tipo: " + producto.getTipoProducto());
            }
        }

        System.out.println("Total de la Factura: " + factura.getTotal());
    }
    
}

