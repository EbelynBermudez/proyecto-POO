package Programa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Logica.Factura;
import Logica.Productos;

public class CrearFactura {
    private static Scanner entrada = new Scanner(System.in);
    private static ArrayList<Productos> listaProductos;
    private static ArrayList<Factura> listaFacturas = new ArrayList<>();
    public static void inicializar(ArrayList<Productos> productos) {
        listaProductos = productos;
    }

    public static void agregarProducto() {
        boolean agregarMasProductos = true;

        System.out.println("Ingrese el número de factura:");
        int numeroFactura = entrada.nextInt();
        
        System.out.println("Ingrese el ID del cajero:");
        int idCajero = entrada.nextInt();

        System.out.println("Ingrese la fecha de la compra (dd/MM/yyyy):");
        String fechaCompraStr = entrada.next();

        while (agregarMasProductos) {
            System.out.println("Ingrese el código del producto que desea agregar al pedido:");
            int codigo = entrada.nextInt();
            Productos productoEncontrado = buscarProductoPorCodigo(codigo);

            if (productoEncontrado != null) {
                ArrayList<Productos> productosComprados = new ArrayList<>();
                productosComprados.add(productoEncontrado);

                System.out.println("Ingrese la cantidad del producto:");
                int cantidad = entrada.nextInt();

                boolean productoRepetido = false;
                for (Productos producto : productosComprados) {
                    if (producto.getCodigo() == codigo) {
                        cantidad += producto.getCantidad();
                        producto.setCantidad(cantidad);
                        productoRepetido = true;
                        System.out.println("Cantidad actualizada.");
                    }
                }

                if (!productoRepetido) {
                    productoEncontrado.setCantidad(cantidad);
                }

                ArrayList<String> ingredientes = CrearProducto.obtenerIngredientesPorCodigo(codigo);

                for (int i = 0; i < ingredientes.size(); i++) {
                    System.out.println((i + 1) + ". " + ingredientes.get(i));
                }

                System.out.println("Desea eliminar algún ingrediente S/N");
                String x = entrada.next();

                if (x.equalsIgnoreCase("S")) {
                    System.out.println("Ingrese el número del ingrediente que desea eliminar (0 para ninguno):");
                    int y = entrada.nextInt();

                    if (y > 0 && y <= ingredientes.size()) {
                        ingredientes.remove(y - 1);
                        System.out.println("Ingrediente eliminado.");
                    } else {
                        System.out.println("No se ha eliminado ningún ingrediente.");
                    }
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);

                try {
                    Date fechaCompra = dateFormat.parse(fechaCompraStr);
                    Factura factura = new Factura(idCajero, numeroFactura, fechaCompra, numeroFactura, productosComprados);
                    listaFacturas.add(factura);
                    System.out.println("Producto agregado al pedido.");
                } catch (ParseException e) {
                    System.out.println("Fecha de compra inválida.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.println("Desea ingresar otro producto S/N:");
            String respuesta = entrada.next();

            if (respuesta.equalsIgnoreCase("N")) {
                agregarMasProductos = false;
            }
        }
    }

    public static void imprimirFactura() {
        System.out.println("Ingrese el número de factura que desea imprimir:");
        int numeroFactura = entrada.nextInt();

        Factura facturaEncontrada = buscarFacturaPorNumero(numeroFactura);

        if (facturaEncontrada != null) {
            System.out.println("Factura:");
            System.out.println("Número de Factura: " + facturaEncontrada.getNumeroFactura());
            System.out.println("Fecha de Compra: " + new SimpleDateFormat("dd/MM/yyyy").format(facturaEncontrada.getFechaCompra()));
            System.out.println("ID del Cajero: " + facturaEncontrada.getIdCajero());
            float total = 0;

            for (Productos producto : facturaEncontrada.getProductosComprados()) {
                System.out.println("Nombre: " + producto.getNombre() + " " + producto.getTipoProducto());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Cantidad: " + producto.getCantidad());
                double subtotal = producto.getCantidad() * producto.getPrecio();
                System.out.println("Subtotal: " + subtotal);
                total += subtotal;
            }

            System.out.println("Total a pagar: " + total);
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    private static Productos buscarProductoPorCodigo(int codigo) {
        for (Productos producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    private static Factura buscarFacturaPorNumero(int numeroFactura) {
        for (Factura factura : listaFacturas) {
            if (factura.getNumeroFactura() == numeroFactura) {
                return factura;
            }
        }
        return null;
    }
    

    

}

