package Programa;

import Logica.Cajero;
import Logica.Combos;
import Logica.Factura;
import Logica.Productos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrearFactura {
    private static Scanner entrada = new Scanner(System.in);
    private static List<Productos> productosFactura = new ArrayList<>();
    
    public static void crearFactura() {

        System.out.println("Ingrese el número de factura:");
        int numeroFactura = entrada.nextInt();

        System.out.println("Ingrese la fecha (dd/mm/aaaa):");
        String fecha = entrada.next();

        System.out.println("Ingrese el ID del cajero:");
        int idCajero = entrada.nextInt();
        Cajero cajero = CrearCajero.buscarCajero(idCajero);

        if (cajero != null) {
            System.out.println("Cajero: " + cajero.getNombre() + " (ID: " + cajero.getId() + ")");
            Factura nuevaFactura = new Factura(idCajero, numeroFactura, fecha, 0.0);
            
            char respuesta;
            System.out.println("¿Desea ingresar un producto o un combo? (P/C)");
            String x = entrada.next();

            if (x.equalsIgnoreCase("P")) {
                do {
                    System.out.println("¿Desea ingresar un producto? (S/N)");
                    respuesta = entrada.next().charAt(0);
                    if (respuesta == 'S' || respuesta == 's') {
                        Productos producto = buscarProducto();
                        if (producto != null) {
                            nuevaFactura.agregarProducto(producto);

                            List<String> ingredientesNumerados = new ArrayList<>();
                            for (int i = 0; i < producto.getIngredientes().size(); i++) {
                                ingredientesNumerados.add((i + 1) + ". " + producto.getIngredientes().get(i));
                            }

                            System.out.println("Ingredientes del producto:");
                            for (String ingrediente : ingredientesNumerados) {
                                System.out.println(ingrediente);
                            }

                            char eliminarIngrediente;
                            do {
                                System.out.println("¿Desea eliminar un ingrediente? (Ingrese el número o '0' para salir)");
                                eliminarIngrediente = entrada.next().charAt(0);
                                if (eliminarIngrediente >= '1' && eliminarIngrediente <= '9') {
                                    int indiceIngrediente = Integer.parseInt(String.valueOf(eliminarIngrediente)) - 1;
                                    if (indiceIngrediente >= 0 && indiceIngrediente < producto.getIngredientes().size()) {
                                        String ingredienteAEliminar = producto.getIngredientes().get(indiceIngrediente);
                                        producto.eliminarIngrediente(ingredienteAEliminar);
                                        System.out.println("Ingrediente eliminado: " + ingredienteAEliminar);
                                        
                                    }
                                }
                            } while (eliminarIngrediente != '0');
                        }
                    } else if (respuesta == 'N' || respuesta == 'n') {
                        System.out.println("Factura creada correctamente.");
                    } else {
                        System.out.println("Opción no válida. Ingrese 'S' para sí o 'N' para no.");
                    }
                } while (respuesta != 'N' && respuesta != 'n');
            } else if (x.equalsIgnoreCase("C")) {
                do {
                    System.out.println("¿Desea ingresar un combo? (S/N)");
                    respuesta = entrada.next().charAt(0);
                    if (respuesta == 'S' || respuesta == 's') {
                        Combos combo = buscarCombo();
                        if (combo != null) {
                            nuevaFactura.agregarCombo(combo);
                        }
                    } else if (respuesta == 'N' || respuesta == 'n') {
                        System.out.println("Factura creada correctamente.");
                    } else {
                        System.out.println("Opción no válida. Ingrese 'S' para sí o 'N' para no.");
                    }
                } while (respuesta != 'N' && respuesta != 'n');
            } else {
                System.out.println("Opción no válida. Ingrese 'P' para producto o 'C' para combo.");
            }
        } else {
            System.out.println("Cajero no encontrado. No se puede crear la factura.");
        }
    }

    
    private static Combos buscarCombo() {
        System.out.println("Ingrese el código del combo:");
        int codigoCombo = entrada.nextInt();
        entrada.nextLine();
        Combos combo = CrearCombo.buscarComboDesdeArchivo("combos.txt", codigoCombo);

        if (combo != null) {
            return combo;
        } else {
            System.out.println("Combo no encontrado o no válido.");
            return null;
        }
    }



    public static void imprimirFactura(Factura factura) {
        if (factura != null) {
            System.out.println("Factura Nº: " + factura.getNumeroFactura());
            System.out.println("Fecha: " + factura.getFechaCompra());
            System.out.println("ID del Cajero: " + factura.getIdCajero());
            System.out.println("==================================");
            System.out.println("Descripción\tPrecio");

            float total = 0;

            if (!factura.getProductosComprados().isEmpty()) {
                System.out.println("Productos comprados:");
                for (Productos producto : factura.getProductosComprados()) {
                    System.out.println("Nombre: " + producto.getNombre() + " " + producto.getTipoProducto());
                    System.out.println("Precio: " + producto.getPrecio());
                    total += producto.getPrecio();
                }
            } else if (!factura.getCombosComprados().isEmpty()) {
                System.out.println("Combos comprados:");
                for (Combos combo : factura.getCombosComprados()) {
                    System.out.println("Nombre del Combo: " + combo.getNombre());
                    System.out.println("Precio del Combo: " + combo.getPrecio());
                    total += combo.getPrecio();
                }
            }

            System.out.println("==================================");
            System.out.println("Total:\t" + total);
        } else {
            System.out.println("Factura no encontrada.");
        }
    }
	


    public static Productos buscarProducto() {
        System.out.println("Ingrese el Código del producto");
        int CodigoBuscado = entrada.nextInt();
        boolean encontrado = false;
        CrearProducto.leerArchivos();
        for (Productos producto : CrearProducto.listaProductos) {
            if (producto.getCodigo() == CodigoBuscado) {
                encontrado = true;
                agregarProductoComprado(producto);
                return producto;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }

        return null;
    }
    public static Cajero buscarCajero() { 
        int idCajero = entrada.nextInt();

        for (Cajero cajero : CrearCajero.listaCajeros) {
            if (cajero.getId() == idCajero) {
                return cajero;
            }
        }

        return null;
    }
    public static void agregarProductoComprado(Productos producto) {
        productosFactura.add(producto);
    }
    public static void imprimirFacturaa() {
        System.out.println("Ingrese el número de factura a imprimir:");
        int numeroFactura = entrada.nextInt();
        
        Factura facturaEncontrada = buscarFacturaPorNumero(numeroFactura);

        if (facturaEncontrada != null) {
            imprimirFactura(facturaEncontrada);
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    public static Factura buscarFacturaPorNumero(int numeroFactura) {
        for (Factura factura : Factura.getFacturas()) {
            if (factura.getNumeroFactura() == numeroFactura) {
                return factura;
            }
        }
        return null; 
    }
    






}
